package com.example.shoppinglist.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HttpSession httpSession;

    public HomeController(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index() {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        return "home";
    }
}
