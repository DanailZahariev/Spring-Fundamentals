package com.example.musicdb.model.entity;

import com.example.musicdb.model.entity.enums.ArtistName;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BasicEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArtistName name;

    @Column(name = "career_information", columnDefinition = "TEXT")
    private String careerInformation;

    public Artist() {
    }

    public ArtistName getName() {
        return name;
    }

    public Artist setName(ArtistName name) {
        this.name = name;
        return this;
    }

    public String getCareerInformation() {
        return careerInformation;
    }

    public Artist setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}
