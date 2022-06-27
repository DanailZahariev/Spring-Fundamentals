package com.example.finalexam.web;

import com.example.finalexam.model.binding.UserLoginBindingModel;
import com.example.finalexam.model.binding.UserRegisterBindingModel;
import com.example.finalexam.model.service.UserServiceModel;
import com.example.finalexam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public UserController(UserService userService,
                          ModelMapper modelMapper,
                          HttpSession httpSession) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }


    @GetMapping("/register")
    public String register() {
        if (httpSession.getAttribute("user") != null) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors() ||
                !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userService.registerUser(userServiceModel);

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
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                    bindingResult);
        }

        UserServiceModel userServiceModel = modelMapper.map(userLoginBindingModel, UserServiceModel.class);
        UserServiceModel loginUser = userService.loginUser(userServiceModel);

        if (loginUser == null) {
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("isNotFound", true);

            return "redirect:login";
        }

        httpSession.setAttribute("user", loginUser);
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout() {
        if (httpSession.getAttribute("user") != null) {
            httpSession.invalidate();
            return "redirect:/";
        }
        return "index";
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
