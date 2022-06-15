package com.example.shoppinglist.web;

import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HttpSession httpSession;
    private final ProductService productService;

    public HomeController(HttpSession httpSession, ProductService productService) {
        this.httpSession = httpSession;
        this.productService = productService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("totalSum", productService.getTotalSum());
        model.addAttribute("drink", productService.findAllByCategoryName(CategoryEnum.DRINK));
        model.addAttribute("food", productService.findAllByCategoryName(CategoryEnum.FOOD));
        model.addAttribute("households", productService.findAllByCategoryName(CategoryEnum.HOUSEHOLD));
        model.addAttribute("other", productService.findAllByCategoryName(CategoryEnum.OTHER));

        return "home";
    }
}
