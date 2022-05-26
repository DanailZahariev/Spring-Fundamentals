package com.example.SpringMobilele.models.entity;

import com.example.SpringMobilele.models.entity.enums.Category;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Column(name = "start_year", nullable = false)
    private int startYear;

    @Column(name = "end_year")
    private int endYear;

    @ManyToOne
    private Brand brand;


    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Model setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Model setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public Model setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public Model setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public Brand getBrand() {
        return brand;
    }

    public Model setBrand(Brand brand) {
        this.brand = brand;
        return this;
    }
}
