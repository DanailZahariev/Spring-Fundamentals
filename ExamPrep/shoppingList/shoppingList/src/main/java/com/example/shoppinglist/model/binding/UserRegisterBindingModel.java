package com.example.shoppinglist.model.binding;

import com.example.shoppinglist.model.validation.UniqueEmail;
import com.example.shoppinglist.model.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegisterBindingModel {

    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @UniqueUsername(message = "Username already exist")
    private String username;

    @NotBlank
    @Email
    @UniqueEmail(message = "Email already exist")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String password;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
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
