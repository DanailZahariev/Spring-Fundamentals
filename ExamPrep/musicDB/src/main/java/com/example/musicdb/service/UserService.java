package com.example.musicdb.service;

import com.example.musicdb.model.entity.User;
import com.example.musicdb.model.service.UserServiceModel;
import com.example.musicdb.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));

        userRepository.save(user);
    }

    public boolean login(UserServiceModel userServiceModel) {

        Optional<User> user = userRepository.findByUsernameIgnoreCase(userServiceModel.getUsername());

        if (user.isEmpty()) {
            return false;
        }

        return passwordEncoder.matches(userServiceModel.getPassword(), user.get().getPassword());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElse(null);
    }
}
