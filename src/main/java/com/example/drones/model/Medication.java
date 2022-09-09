package com.example.drones.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class Medication {
   @Pattern(regexp = "^[a-zA-Z0-9_\\-]+$", message = "Medication name should contain only letters, numbers, ‘-‘, ‘_’")
   private String name;

   private int weight;

   @Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication code should contain only upper case letters, underscore and numbers")
   private String code;

   private byte[] picture;
}
