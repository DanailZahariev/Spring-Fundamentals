package bg.coffeeShop.web;

import bg.coffeeShop.models.service.UserServiceModel;
import bg.coffeeShop.models.binding.UserLoggingBindingModel;
import bg.coffeeShop.models.binding.UserRegisterBindingModel;
import bg.coffeeShop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;

    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {

        if (!model.containsAttribute("isNotFound")) {
            model.addAttribute("isNotFound", true);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoggingBindingModel userLoggingBindingModel, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("userLoggingBindingModel", userLoggingBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoggingBindingModel", bindingResult);

            return "redirect:login";
        }

        UserServiceModel userServiceModel = userService
                .findByUsernameAndPassword(userLoggingBindingModel.getUsername(), userLoggingBindingModel.getPassword());

        if (userServiceModel == null) {

            redirectAttributes.addFlashAttribute("userLoggingBindingModel", userLoggingBindingModel);
            redirectAttributes.addFlashAttribute("isNotFound", false);

            return "redirect:login";
        }

        userService.loginUser(userServiceModel.getId(), userLoggingBindingModel.getUsername());

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoggingBindingModel userLoggingBindingModel() {
        return new UserLoggingBindingModel();
    }
}
