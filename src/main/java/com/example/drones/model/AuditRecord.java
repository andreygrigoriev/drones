package com.example.drones.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
@Getter
@Setter
@Builder
@IdClass(AuditRecord.PrimaryKey.class)
public class AuditRecord {
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name = "record_name")
   private String name;
   @Id
   private String entity;
   @Id
   private LocalDateTime logDate;

   @Column(name = "record_value")
   private String value;

   @Data
   @NoArgsConstructor
   @AllArgsConstructor
   @Builder
   public static class PrimaryKey implements Serializable {
      @Serial
      private static final long serialVersionUID = 1L;
      String name;
      String entity;
      @Builder.Default
      LocalDateTime logDate = LocalDateTime.now();
   }
}
