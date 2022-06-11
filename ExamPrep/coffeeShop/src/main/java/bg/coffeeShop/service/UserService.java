package bg.coffeeShop.service;

import bg.coffeeShop.models.service.UserServiceModel;
import bg.coffeeShop.models.entity.User;
import bg.coffeeShop.models.view.UserViewModel;
import bg.coffeeShop.repository.UserRepository;
import bg.coffeeShop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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


    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);

        return modelMapper.map(userRepository.save(user), UserServiceModel.class);
    }

    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(user -> modelMapper.map(user, UserServiceModel.class)).orElse(null);
    }

    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean userIsNotLogged() {
        return currentUser.getId() == null;
    }

    public List<UserViewModel> findAllUserAndCountOfOrders() {
        return userRepository.findAllByOrdersCountDes().stream().map(user -> {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setUsername(user.getUsername());
            userViewModel.setOrderCount(user.getOrders().size());

            return userViewModel;
        }).collect(Collectors.toList());
    }
}
