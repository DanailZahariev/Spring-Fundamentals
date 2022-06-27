package com.example.finalexam.model.view;

public class SongViewModel {

    private Long id;
    private String performer;
    private String tittle;
    private Integer duration;

    public SongViewModel() {
    }

    public Long getId() {
        return id;
    }

    public SongViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPerformer() {
        return performer;
    }

    public SongViewModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public SongViewModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public SongViewModel setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }
}
