package com.example.drones.service;

import com.example.drones.model.Drone;
import com.example.drones.model.Medication;
import com.example.drones.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DroneService {
   private final DroneRepository repository;
   private final AuditService auditService;

   private static final String MSG_DRONE_NOT_FOUND = "Drone is not found for serial number %s";
   private static final String MSG_BATTERY_LEVEL_TOO_LOW = "Cannot perform items load. Battery level is too low (%s)";
   private static final String MSG_WEIGHT_LIMIT_EXCEEDED = "Cannot perform item load. Drone weight limit (%s) exceeded (%s)";
   private static final byte BATTERY_LEVEL_TRESHOLD = 25;

   public Drone register(Drone drone) {
      return repository.save(drone);
   }

   public Drone loadMedication(String droneSerialNumber, Medication medication) {
      Drone drone = findDrone(droneSerialNumber);
      validateBatteryLevel(drone);
      validateLoadedWeight(drone, medication);

      List<Medication> items = drone.getItems();
      items.add(medication);
      drone.setItems(items);
      repository.save(drone);
      return drone;
   }

   public List<Medication> listMedications(String droneSerialNumber) {
      return findDrone(droneSerialNumber).getItems();
   }

   public List<Drone> listAvailableDrones() {
      return repository.findByState(Drone.State.IDLE);
   }

   public Drone findDrone(String droneSerialNumber) {
      return repository.findById(droneSerialNumber)
            .orElseThrow(() -> new RuntimeException(String.format(MSG_DRONE_NOT_FOUND, droneSerialNumber)));
   }

   private void validateBatteryLevel(Drone drone) {
      byte batteryLevel = drone.getBatteryCapacity();
      if (batteryLevel < BATTERY_LEVEL_TRESHOLD) {
         throw new RuntimeException(String.format(MSG_BATTERY_LEVEL_TOO_LOW, batteryLevel));
      }
   }

   private void validateLoadedWeight(Drone drone, Medication medication) {
      int weight = medication.getWeight();
      if (weight > drone.getWeightLimit()) {
         throw new RuntimeException(String.format(MSG_WEIGHT_LIMIT_EXCEEDED, drone.getWeightLimit(), getCurrentWeight(drone)));
      }
   }

   private int getCurrentWeight(Drone drone) {
      return drone.getItems().stream().mapToInt(Medication::getWeight).sum();
   }

   @Scheduled(cron = "${application.scheduler.audit-battery-level-cron}")
   @SuppressWarnings("unused")
   private void auditBatteryLevel() {
      log.info("Battery level audit started");
      repository.findAll().forEach(drone -> {
         auditService.auditAction("Battery level", drone.getSerialNumber(), String.valueOf(drone.getBatteryCapacity()));
      });
      log.info("Battery level audit finished");
   }
}
