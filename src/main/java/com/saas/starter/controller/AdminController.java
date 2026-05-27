package com.saas.starter.controller;

import com.saas.starter.model.AuditLog;
import com.saas.starter.model.Tenant;
import com.saas.starter.security.JwtAuthenticationFilter;
import com.saas.starter.service.AuditService;
import com.saas.starter.service.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
public class AdminController {

    private final TenantService tenantService;
    private final AuditService auditService;

    public AdminController(TenantService tenantService, AuditService auditService) {
        this.tenantService = tenantService;
        this.auditService = auditService;
    }

    @GetMapping("/tenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        return ResponseEntity.ok(tenantService.getAllTenants());
    }

    @PutMapping("/tenants/{id}/plan")
    public ResponseEntity<Tenant> updatePlan(@PathVariable String id, @RequestBody String plan) {
        return ResponseEntity.ok(tenantService.updatePlan(id, plan));
    }

    @GetMapping("/audit-logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs(@RequestParam(required = false) String tenantId) {
        if (tenantId != null) {
            return ResponseEntity.ok(auditService.getTenantLogs(tenantId));
        }
        return ResponseEntity.ok(auditService.getTenantLogs("default"));
    }
}
