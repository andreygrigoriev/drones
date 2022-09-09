package com.example.drones.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "drones")
@Getter
@Setter
public class Drone {
   @NotBlank(message = "'Serial Number' parameter should not be blank")
   @Size(max = 100, message = "'Serial Number' parameter should not have more than 100 characters")
   private String serialNumber;

   @Enumerated(value = EnumType.STRING)
   private Model model;

   private final int weightLimit = 500;

   @PositiveOrZero(message = "'Battery Capacity' parameter should not be negative")
   @Max(value = 100, message = "'Battery Capacity' parameter should be 100 or less")
   private byte batteryCapacity;

   @Enumerated(value = EnumType.STRING)
   private State state;

   private List<Medication> items;

   public static enum State {
      IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
   }

   public static enum Model {
      LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
   }
}
