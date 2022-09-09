package com.example.drones.repository;

import com.example.drones.model.AuditRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends CrudRepository<AuditRecord, String> {
}
