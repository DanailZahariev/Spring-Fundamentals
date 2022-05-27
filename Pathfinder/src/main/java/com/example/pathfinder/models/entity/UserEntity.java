package com.example.pathfinder.models.entity;

import com.example.pathfinder.models.entity.enums.UserLevel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BasicEntity {

    @Column(name = "age")
    private Integer age;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email")
    private String email;

    @ManyToMany
    private Set<RoleEntity> role;

    @Enumerated(EnumType.STRING)
    private UserLevel level;

    public UserEntity() {
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public UserEntity setRole(Set<RoleEntity> role) {
        this.role = role;
        return this;
    }

    public UserLevel getLevel() {
        return level;
    }

    public UserEntity setLevel(UserLevel level) {
        this.level = level;
        return this;
    }
}
