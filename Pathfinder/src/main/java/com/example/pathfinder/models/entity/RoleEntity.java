package com.example.pathfinder.models.entity;

import com.example.pathfinder.models.entity.enums.RoleName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity extends BasicEntity {

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public RoleEntity() {
    }

    public RoleName getName() {
        return name;
    }

    public RoleEntity setName(RoleName name) {
        this.name = name;
        return this;
    }
}
