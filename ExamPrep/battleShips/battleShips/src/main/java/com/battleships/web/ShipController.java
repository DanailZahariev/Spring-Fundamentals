package com.battleships.web;

import com.battleships.model.binding.ShipAddingBindingModel;
import com.battleships.model.entity.Category;
import com.battleships.model.service.ShipServiceModel;
import com.battleships.model.service.UserServiceModel;
import com.battleships.service.CategoryService;
import com.battleships.service.ShipService;
import com.battleships.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/ship")
public class ShipController {

    private final ShipService shipService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final HttpSession httpSession;

    public ShipController(ShipService shipService, CategoryService categoryService,
                          UserService userService, ModelMapper modelMapper, HttpSession httpSession) {
        this.shipService = shipService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.httpSession = httpSession;
    }


    @GetMapping("/add")
    public String add() {

        if (httpSession.getAttribute("user") != null) {
            return "ship-add";
        }

        return "redirect:/";
    }


    @PostMapping("/add")
    public String addShip(@Valid ShipAddingBindingModel shipAddingBindingModel, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("shipAddingBindingModel", shipAddingBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.shipAddingBindingModel", bindingResult);

            return "redirect:add";
        }


        ShipServiceModel serviceModel = modelMapper.map(shipAddingBindingModel, ShipServiceModel.class);
        Category category = categoryService.findCategory(shipAddingBindingModel.getCategory());
        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        serviceModel.setCategory(category);

        shipService.addShip(serviceModel, userService.findByUsername(currentUser.getUsername()) );

        return "redirect:/";
    }

    @ModelAttribute
    public ShipAddingBindingModel shipAddingBindingModel() {
        return new ShipAddingBindingModel();
    }
}
