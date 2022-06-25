package com.example.gira.web;

import com.example.gira.model.binding.TaskAddBindingModel;
import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.service.TaskService;
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
@RequestMapping("/tasks")
public class TaskController {

    private final HttpSession httpSession;
    private final ModelMapper modelMapper;
    private final TaskService taskService;

    public TaskController(HttpSession httpSession,
                          ModelMapper modelMapper,
                          TaskService taskService) {
        this.httpSession = httpSession;
        this.modelMapper = modelMapper;
        this.taskService = taskService;
    }


    @GetMapping("/add")
    public String add() {
        if (httpSession.getAttribute("user") != null) {
            return "add-task";
        }
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(@Valid TaskAddBindingModel taskAddBindingModel,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("taskAddBindingModel", taskAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.taskAddBindingModel",
                    bindingResult);
            return "redirect:add";
        }

        TaskServiceModel taskServiceModel = modelMapper.map(taskAddBindingModel, TaskServiceModel.class);
        UserServiceModel currentUser = modelMapper.map(httpSession.getAttribute("user"), UserServiceModel.class);
        taskService.addTask(taskServiceModel, currentUser);

        return "redirect:/";
    }

    @ModelAttribute
    public TaskAddBindingModel taskAddBindingModel() {
        return new TaskAddBindingModel();
    }
}
