package com.example.pathfinder.models.binding;

import com.example.pathfinder.models.entity.enums.CategoryName;
import com.example.pathfinder.models.entity.enums.UserLevel;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class RoutesAddBindingModel {

    @Size(min = 3, max = 20, message = "Routes name must be between 3 and 20 characters")
    private String name;

    @Size(min = 3)
    private String description;

    private MultipartFile gpxCoordinates;

    @NotNull
    private UserLevel level;

    private String videoUrl;

    private Set<CategoryName> categories;


    public RoutesAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public RoutesAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoutesAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RoutesAddBindingModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public UserLevel getLevel() {
        return level;
    }

    public RoutesAddBindingModel setLevel(UserLevel level) {
        this.level = level;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RoutesAddBindingModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<CategoryName> getCategories() {
        return categories;
    }

    public RoutesAddBindingModel setCategories(Set<CategoryName> categories) {
        this.categories = categories;
        return this;
    }
}
