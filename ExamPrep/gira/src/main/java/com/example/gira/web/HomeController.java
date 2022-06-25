package com.example.gira.web;

import com.example.gira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final HttpSession httpSession;
    private final TaskService taskService;

    public HomeController(HttpSession httpSession,
                          TaskService taskService) {
        this.httpSession = httpSession;
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String index(Model model) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }

        model.addAttribute("allTask", taskService.showAllTasks());

        return "home";
    }

    @GetMapping("/progress/{id}")
    public String progress(@PathVariable Long id){

        taskService.progressTask(id);
        return "redirect:/";
    }
}
