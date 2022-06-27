package com.example.finalexam.model.service;

import com.example.finalexam.model.entity.enums.StyleEnum;

import java.time.LocalDate;

public class SongServiceModel {

    private Long id;
    private String performer;
    private String tittle;
    private Integer duration;
    private LocalDate releaseDate;
    private StyleEnum style;

    public SongServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public SongServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongServiceModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SongServiceModel setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongServiceModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public SongServiceModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public SongServiceModel setStyle(StyleEnum style) {
        this.style = style;
        return this;
    }
}
