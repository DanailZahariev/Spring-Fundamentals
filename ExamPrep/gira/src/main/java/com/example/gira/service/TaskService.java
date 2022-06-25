package com.example.gira.service;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.Task;
import com.example.gira.model.entity.User;
import com.example.gira.model.entity.enums.ProgressEnum;
import com.example.gira.model.entity.view.TaskViewModel;
import com.example.gira.model.service.TaskServiceModel;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.repository.TaskRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ClassificationService classificationService;
    private final ModelMapper modelMapper;

    public TaskService(TaskRepository taskRepository,
                       ClassificationService classificationService,
                       ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.classificationService = classificationService;
        this.modelMapper = modelMapper;
    }

    public void addTask(TaskServiceModel taskServiceModel, UserServiceModel currentUser) {

        Task task = modelMapper.map(taskServiceModel, Task.class);
        Classification classification = classificationService.findClassificationByName(taskServiceModel.getClassification().getName());
        task.setUser(modelMapper.map(currentUser, User.class));
        task.setProgress(ProgressEnum.OPEN);
        task.setClassification(classification);

        taskRepository.save(task);
    }

    public List<TaskViewModel> showAllTasks() {
        return taskRepository.findAll().stream().map(task -> modelMapper.map(task, TaskViewModel.class)).collect(Collectors.toList());
    }

    public void progressTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        String progressName = task.get().getProgress().name();

        if (progressName.equals("OPEN")) {
            task.get().setProgress(ProgressEnum.IN_PROGRESS);
            taskRepository.save(task.get());
        } else if (progressName.equals("IN_PROGRESS")) {
            task.get().setProgress(ProgressEnum.COMPLETED);
            taskRepository.save(task.get());
        } else if (progressName.equals("COMPLETED")) {
            taskRepository.deleteById(id);
        }
    }
}
