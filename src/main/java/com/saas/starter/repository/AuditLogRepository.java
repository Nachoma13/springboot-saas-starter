package com.saas.starter.repository;

import com.saas.starter.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByTenantIdOrderByCreatedAtDesc(String tenantId);
    List<AuditLog> findByUserIdOrderByCreatedAtDesc(Long userId);
}
