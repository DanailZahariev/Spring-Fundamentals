package com.example.finalexam.model.binding;

import com.example.finalexam.model.validation.UniqueEmail;
import com.example.finalexam.model.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotBlank(message = "Username cannot be empty.")
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters.")
    @UniqueUsername(message = "Username already exist.")
    private String username;

    @NotBlank(message = "Email cannot be empty.")
    @Email
    @UniqueEmail(message = "Email already exist.")
    private String email;

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    private String password;

    @NotBlank
    @Size(min = 3, max = 20)
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
