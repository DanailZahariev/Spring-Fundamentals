package com.example.pathfinder.models.view;

import com.example.pathfinder.models.entity.enums.UserLevel;

public class UserViewModel {

    private Long id;
    private String fullName;
    private String username;
    private Integer age;
    private UserLevel level;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public UserLevel getLevel() {
        return level;
    }

    public UserViewModel setLevel(UserLevel level) {
        this.level = level;
        return this;
    }
}
