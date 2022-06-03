package com.example.SpringMobilele.models.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BasicEntity {

    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Model> models;

    public String getName() {
        return name;
    }

    public Brand() {
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
