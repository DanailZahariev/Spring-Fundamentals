package com.example.finalexam.model.binding;

import com.example.finalexam.model.entity.enums.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddSongBindingModel {

    @NotBlank(message = "Performer cannot be blank.")
    @Size(min = 3, max = 20, message = "Performer name length must be between 3 and 20 characters ")
    private String performer;

    @NotBlank(message = "Tittle cannot be blank.")
    @Size(min = 2, max = 20, message = "Title length must be between 2 and 20 characters ")
    private String tittle;

    @PastOrPresent(message = "The date cannot be in the future!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate releaseDate;

    @Positive(message = "Duration must be positive!")
    @NotNull
    private Integer duration;

    @NotNull(message = "You must select a style!")
    private String style;


    public AddSongBindingModel() {
    }

    public String getPerformer() {
        return performer;
    }

    public AddSongBindingModel setPerformer(String performer) {
        this.performer = performer;
        return this;
    }

    public String getTittle() {
        return tittle;
    }

    public AddSongBindingModel setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AddSongBindingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public AddSongBindingModel setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getStyle() {
        return style;
    }

    public AddSongBindingModel setStyle(String style) {
        this.style = style;
        return this;
    }
}
