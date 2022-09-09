package com.example.drones.repository;

import com.example.drones.model.Drone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends CrudRepository<Drone, String> {
   List<Drone> findByState(Drone.State state);
}
