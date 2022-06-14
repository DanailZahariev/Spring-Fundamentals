package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.User;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       HttpSession httpSession,
                       ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.httpSession = httpSession;
        this.modelMapper = modelMapper;
    }

    public void registerUser(UserServiceModel userServiceModel) {
        User user = modelMapper.map(userServiceModel, User.class);
        user.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        userRepository.save(user);
    }

    public boolean isFreeEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public boolean isFreeUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    public boolean loginUser(UserServiceModel userServiceModel) {
        Optional<User> optionalUser = userRepository.findByUsername(userServiceModel.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(userServiceModel.getPassword(), optionalUser.get().getPassword());
    }
}
