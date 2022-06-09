package com.example.pathfinder.web;

import com.example.pathfinder.models.binding.RoutesAddBindingModel;
import com.example.pathfinder.models.service.RouteServiceModel;
import com.example.pathfinder.service.RouteService;
import com.example.pathfinder.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;


    public RouteController(RouteService routeService, CurrentUser currentUser, ModelMapper modelMapper) {
        this.routeService = routeService;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    private String allRoutes(Model model) {

        model.addAttribute("routes", routeService
                .findAllRoutesView());

        return "routes";
    }

    @GetMapping("/add")
    public String add() {

        if (isLoggedUser()) {
            return "redirect/users/login";
        }

        return "add-route";
    }

    @PostMapping("/add")
    public String addRoute(@Valid RoutesAddBindingModel routesAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("routesAddBindingModel", routesAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResul.routesAddBindingModel", bindingResult);


            return "redirect:add";
        }

        RouteServiceModel routeServiceModel = modelMapper.map(routesAddBindingModel, RouteServiceModel.class);
        routeServiceModel.setGpxCoordinates(new String(routesAddBindingModel.getGpxCoordinates().getBytes()));

        routeService.addNewRoute(routeServiceModel);

        return "redirect:all";
    }

    @ModelAttribute
    public RoutesAddBindingModel routesAddBindingModel() {
        return new RoutesAddBindingModel();
    }

    private boolean isLoggedUser() {
        return currentUser.getId() == null;
    }
}
