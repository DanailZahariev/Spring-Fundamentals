package com.example.SpringMobilele.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Model> models = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Brand setName(String name) {
        this.name = name;
        return this;
    }

    public List<Model> getModels() {
        return models;
    }

    public Brand setModels(List<Model> models) {
        this.models = models;
        return this;
    }
}
