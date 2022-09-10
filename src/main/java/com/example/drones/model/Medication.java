package com.example.drones.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
   private static final long serialVersionUID = 1L;

   @Pattern(regexp = "^[a-zA-Z0-9_\\-]+$", message = "Medication name should contain only letters, numbers, ‘-‘, ‘_’")
   private String name;

   private int weight;

   @Id
   @Pattern(regexp = "^[A-Z0-9_]+$", message = "Medication code should contain only upper case letters, underscore and numbers")
   private String code;

   private byte[] picture;
}
