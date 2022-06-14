package com.example.shoppinglist.web;

import com.example.shoppinglist.model.binding.UserLoginBindingModel;
import com.example.shoppinglist.model.binding.UserRegisterBindingModel;
import com.example.shoppinglist.model.service.UserServiceModel;
import com.example.shoppinglist.service.UserService;
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
    private final HttpSession httpSession;

    public UserController(UserService userService, ModelMapper modelMapper,
                          HttpSession httpSession) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        userService.registerUser(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));
        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (httpSession.getAttribute("user") != null) {
            return "redirect:home";
        }
        if (!model.containsAttribute("isNotFound")) {
            model.addAttribute("isNotFound", false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginBindingModel userLoginBindingModel,
                            BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }

        UserServiceModel userServiceModel = modelMapper.map(userLoginBindingModel, UserServiceModel.class);
        boolean loginUser = userService.loginUser(userServiceModel);

        if (!loginUser) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("isNotFound", true);
            return "redirect:login";
        }

        httpSession.setAttribute("user", userLoginBindingModel);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel() {
        return new UserLoginBindingModel();
    }
}
