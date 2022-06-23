package com.example.gira.service;

import com.example.gira.model.entity.User;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        userRepository.save(user);
    }

    public boolean isFreeUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    public boolean isFreeEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public boolean loginUser(UserServiceModel userServiceModel) {

        Optional<User> loginUser = userRepository.findByEmailIgnoreCase(userServiceModel.getEmail());

        if (loginUser.isEmpty()) {

            return false;
        }

        return passwordEncoder.matches(userServiceModel.getPassword(), loginUser.get().getPassword());
    }
}
