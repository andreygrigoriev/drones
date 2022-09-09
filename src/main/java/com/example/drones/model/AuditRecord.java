package com.example.drones.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit")
@Getter
@Setter
@Builder
public class AuditRecord {
   private String name;
   private String entity;
   private LocalDateTime logDate;
   private String value;
}
