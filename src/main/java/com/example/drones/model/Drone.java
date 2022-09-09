package com.example.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Transient;
import javax.validation.constraints.*;

@Data
public class Drone {
   @NotBlank(message = "'Serial Number' parameter should not be blank")
   @Size(max = 100, message = "'Serial Number' parameter should not have more than 100 characters")
   private String serialNumber;

   private Model model;

   @Transient
   @JsonIgnore
   private static final int weightLimit = 500;

   @PositiveOrZero(message = "'Battery Capacity' parameter should not be negative")
   @Max(value = 100, message = "'Battery Capacity' parameter should be 100 or less")
   private byte batteryCapacity;

   private State state;
}
