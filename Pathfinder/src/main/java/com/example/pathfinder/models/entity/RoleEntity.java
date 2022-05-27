package com.example.pathfinder.models.entity;

import com.example.pathfinder.models.entity.enums.RoleName;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity extends BasicEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleName role;

    public RoleEntity() {
    }

    public RoleName getRole() {
        return role;
    }

    public RoleEntity setRole(RoleName role) {
        this.role = role;
        return this;
    }
}
