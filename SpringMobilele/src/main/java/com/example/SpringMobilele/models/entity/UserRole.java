package com.example.SpringMobilele.models.entity;

import com.example.SpringMobilele.models.entity.enums.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role userRole;

    public Long getId() {
        return id;
    }

    public UserRole setId(Long id) {
        this.id = id;
        return this;
    }

    public Role getUserRole() {
        return userRole;
    }

    public UserRole setUserRole(Role userRole) {
        this.userRole = userRole;
        return this;
    }
}
