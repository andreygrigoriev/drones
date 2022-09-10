package com.example.drones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "drones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
   private static final long serialVersionUID = 1L;

   @Id
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

   @ElementCollection(targetClass = Medication.class, fetch = FetchType.EAGER)
   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
   @CollectionTable(joinColumns = @JoinColumn(name = "DRONE_ID"))
   private List<Medication> items;

   public static enum State {
      IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
   }

   public static enum Model {
      LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
   }
}
