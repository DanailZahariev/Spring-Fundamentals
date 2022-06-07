package com.example.pathfinder.models.service;

import com.example.pathfinder.models.entity.RoleEntity;
import com.example.pathfinder.models.entity.enums.UserLevel;

import java.util.Set;

public class UserServiceModel {

    private Long id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private UserLevel level;
    private Set<RoleEntity> role;


    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UserServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserServiceModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserServiceModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public UserLevel getLevel() {
        return level;
    }

    public UserServiceModel setLevel(UserLevel level) {
        this.level = level;
        return this;
    }

    public Set<RoleEntity> getRole() {
        return role;
    }

    public UserServiceModel setRole(Set<RoleEntity> role) {
        this.role = role;
        return this;
    }
}
