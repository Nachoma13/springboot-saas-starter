package com.saas.starter.service;

import com.saas.starter.model.AuditLog;
import com.saas.starter.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public void log(String action, String entityType, Long userId, String details, String tenantId, String ipAddress) {
        AuditLog log = new AuditLog(action, entityType, userId, details, tenantId, ipAddress);
        auditLogRepository.save(log);
    }

    public List<AuditLog> getTenantLogs(String tenantId) {
        return auditLogRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    public List<AuditLog> getUserLogs(Long userId) {
        return auditLogRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }
}
