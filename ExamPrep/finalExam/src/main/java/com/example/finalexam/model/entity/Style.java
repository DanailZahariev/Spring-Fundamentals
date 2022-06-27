package com.example.finalexam.model.entity;

import com.example.finalexam.model.entity.enums.StyleEnum;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BasicEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private StyleEnum styleName;

    private String description;

    public Style() {
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
