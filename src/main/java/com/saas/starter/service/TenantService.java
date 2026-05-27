package com.saas.starter.service;

import com.saas.starter.model.Tenant;
import com.saas.starter.repository.TenantRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Tenant getTenant(String id) {
        return tenantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant not found: " + id));
    }

    public Tenant updatePlan(String id, String plan) {
        Tenant tenant = getTenant(id);
        tenant.setPlan(plan);
        return tenantRepository.save(tenant);
    }
}
