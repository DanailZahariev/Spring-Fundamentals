package com.example.pathfinder.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BasicEntity {

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String url;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private RouteEntity route;

    public PictureEntity() {
    }

    public String getTitle() {
        return title;
    }

    public PictureEntity setTitle(String tittle) {
        this.title = tittle;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public PictureEntity setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public PictureEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public PictureEntity setRoute(RouteEntity route) {
        this.route = route;
        return this;
    }
}
