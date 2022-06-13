package com.battleships.service;

import com.battleships.model.entity.User;
import com.battleships.model.service.UserServiceModel;
import com.battleships.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    public UserService(UserRepository userRepository, ModelMapper modelMapper,
                       PasswordEncoder passwordEncoder, HttpSession httpSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
    }

    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userRepository.save(user);
    }

    public boolean loginUser(UserServiceModel userServiceModel) {

        Optional<User> byUsername = userRepository.findByUsername(userServiceModel.getUsername());

        if (byUsername.isEmpty()) {
            httpSession.invalidate();
            return false;
        }

        return passwordEncoder.matches(userServiceModel.getPassword(), byUsername.get().getPassword());
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserServiceModel findByUsername(String username) {
        return modelMapper.map(userRepository.findByUsername(username)
                .orElse(null), UserServiceModel.class);
    }
}
