package com.example.drones.service;

import com.example.drones.model.AuditRecord;
import com.example.drones.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {
   private final AuditRepository auditRepository;

   public void auditAction(String name, String entity, String value) {
      AuditRecord record = AuditRecord.builder()
            .name(name)
            .entity(entity)
            .value(value)
            .logDate(LocalDateTime.now())
            .build();
      auditRepository.save(record);
   }
}
