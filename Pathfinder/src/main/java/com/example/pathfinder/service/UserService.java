package com.example.pathfinder.service;

import com.example.pathfinder.models.entity.UserEntity;
import com.example.pathfinder.models.entity.enums.UserLevel;
import com.example.pathfinder.models.service.UserServiceModel;
import com.example.pathfinder.repository.UserRepository;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }


    public void registerUser(UserServiceModel userServiceModel) {

        UserEntity user = modelMapper.map(userServiceModel, UserEntity.class);
        user.setLevel(UserLevel.BEGINNER);

        userRepository.save(user);
    }

    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class)).orElse(null);
    }

    public void loginUser(Long id, String username) {
        currentUser.setUsername(username);
        currentUser.setId(id);
    }

    public void logout() {
        currentUser.setId(null);
        currentUser.setUsername(null);
    }

    public UserServiceModel findById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class)).orElse(null);
    }

    public boolean isNameExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public UserEntity findCurrentLoggedUser() {
        return userRepository.findById(currentUser.getId()).orElse(null);
    }
}
