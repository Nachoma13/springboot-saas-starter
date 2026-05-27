package com.saas.starter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
public class AuditLog {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String action;
    @Column(nullable = false) private String entityType;
    private Long entityId;
    private Long userId;
    @Column(columnDefinition = "TEXT") private String details;
    @Column(nullable = false) private String tenantId;
    private String ipAddress;
    private LocalDateTime createdAt = LocalDateTime.now();

    public AuditLog() {}
    public AuditLog(String action, String entityType, Long userId, String details, String tenantId, String ipAddress) {
        this.action = action; this.entityType = entityType; this.userId = userId;
        this.details = details; this.tenantId = tenantId; this.ipAddress = ipAddress;
    }

    public Long getId() { return id; }
    public String getAction() { return action; }
    public String getEntityType() { return entityType; }
    public Long getEntityId() { return entityId; }
    public Long getUserId() { return userId; }
    public String getDetails() { return details; }
    public String getTenantId() { return tenantId; }
    public String getIpAddress() { return ipAddress; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
