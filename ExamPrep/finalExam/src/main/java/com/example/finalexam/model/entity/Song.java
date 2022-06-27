package com.example.finalexam.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "songs")
public class Song extends BasicEntity {

    @Column(nullable = false, unique = true)
    private String performer;

    @Column(nullable = false)
    private String tittle;

    @Column(nullable = false)
    private Integer duration;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne(optional = false)
    private Style style;

    @ManyToMany(mappedBy = "songs")
    private List<User> users;

    public Song() {
    }

    public String getPerformer() {
        return performer;
    }

    public Song setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public Song setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public Song setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Song setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Song setStyle(Style style) {
        this.style = style;
        return this;
    }
}
