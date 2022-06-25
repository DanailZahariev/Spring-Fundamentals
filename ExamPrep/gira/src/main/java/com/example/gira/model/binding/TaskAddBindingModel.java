package com.example.gira.model.binding;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.User;
import com.example.gira.model.entity.enums.ClassificationName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskAddBindingModel {

    @NotBlank(message = "Name cannot be blank.")
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank(message = "Description cannot be blank.")
    @Size(min = 5)
    private String description;

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate dueDate;

    @NotNull
    private ClassificationName classification;


    public TaskAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public TaskAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskAddBindingModel setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationName getClassification() {
        return classification;
    }

    public TaskAddBindingModel setClassification(ClassificationName classification) {
        this.classification = classification;
        return this;
    }
}
