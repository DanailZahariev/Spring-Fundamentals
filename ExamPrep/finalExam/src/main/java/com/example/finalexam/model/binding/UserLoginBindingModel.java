package com.example.finalexam.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    @NotBlank(message = "Username cannot be blank.")
    @Email(message = "Invalid username.")
    private String username;
    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 3, max = 20, message = "Password should be between 3 and 20 characters.")
    private String password;

    public UserLoginBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
