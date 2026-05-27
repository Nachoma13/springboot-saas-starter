package com.saas.starter.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id private String id;
    @Column(unique = true, nullable = false) private String name;
    private String plan = "free";
    private boolean active = true;
    private LocalDateTime createdAt = LocalDateTime.now();

    public Tenant() {}
    public Tenant(String id, String name) { this.id = id; this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
