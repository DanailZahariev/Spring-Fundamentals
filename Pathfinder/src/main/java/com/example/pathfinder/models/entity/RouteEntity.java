package com.example.pathfinder.models.entity;

import com.example.pathfinder.models.entity.enums.UserLevel;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BasicEntity {

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private UserLevel level;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private UserEntity author;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToMany
    private Set<CategoryEntity> categories;

    public RouteEntity() {
    }

    public String getDescription() {
        return description;
    }

    public RouteEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RouteEntity setGpxCoordinates(String gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public UserLevel getLevel() {
        return level;
    }

    public RouteEntity setLevel(UserLevel level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RouteEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public RouteEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }
}
