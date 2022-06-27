package com.example.finalexam.service;

import com.example.finalexam.model.entity.Song;
import com.example.finalexam.model.entity.User;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public boolean isFreeEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public boolean isFreeUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    public UserServiceModel loginUser(UserServiceModel userServiceModel) {

        Optional<User> loginUser = userRepository.findByUsernameIgnoreCase(userServiceModel.getUsername());

        if (loginUser.isEmpty()) {
            return null;
        }

        boolean passwordMatches = passwordEncoder.matches(userServiceModel.getPassword(), loginUser.get().getPassword());
        if (!passwordMatches) {
            return null;
        }

        return modelMapper.map(loginUser, UserServiceModel.class);
    }

    public User findById(UserServiceModel currentUser) {
        return userRepository.findById(currentUser.getId()).orElse(null);
    }

    public void saveUserSong(User user) {
        userRepository.save(user);
    }

    public Double getTotalDuration(UserServiceModel currentUser) {

        User user = userRepository.findById(currentUser.getId()).orElse(null);

        int sum = user.getSongs().stream().mapToInt(Song::getDuration).sum();

        return (double) (sum / 60);
    }

    public List<Song> currentPlaylist(UserServiceModel currentUser) {

        User user = userRepository.findById(currentUser.getId()).orElse(null);

        return user.getSongs();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username).orElse(null);
    }

}
