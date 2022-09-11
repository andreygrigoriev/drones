package com.example.drones.controller;

import com.example.drones.model.Drone;
import com.example.drones.model.Medication;
import com.example.drones.service.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("/api/v1/drones")
public class DroneController {
   private final DroneService droneService;

   @PostMapping("/register")
   public Drone registerDrone(@Valid @RequestBody Drone drone) {
      return droneService.register(drone);
   }

   @PostMapping("/{droneSerialNumber}/load")
   public ResponseEntity<Void> loadMedication(@Valid @RequestBody Medication medication, @PathVariable String droneSerialNumber) {
      droneService.loadMedication(droneSerialNumber, medication);
      return ResponseEntity.ok().build();
   }

   @GetMapping("/{droneSerialNumber}/items")
   public List<Medication> listMedication(@PathVariable String droneSerialNumber) {
      return droneService.listMedications(droneSerialNumber);
   }

   @GetMapping("/available")
   public List<Drone> availableDrones() {
      return droneService.listAvailableDrones();
   }

   @GetMapping("/{droneSerialNumber}/battery")
   public Byte getBatteryLevel(@PathVariable String droneSerialNumber) {
      return droneService.findDrone(droneSerialNumber).getBatteryCapacity();
   }
}
