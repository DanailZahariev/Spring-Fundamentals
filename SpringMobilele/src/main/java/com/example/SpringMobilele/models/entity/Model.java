package com.example.SpringMobilele.model.entity;

import com.example.SpringMobilele.model.entity.enums.Category;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class Model extends BasicEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String imageUrl;

    private int startYear;

    private int endYear;

    @ManyToOne
    private Brand brand;
}
