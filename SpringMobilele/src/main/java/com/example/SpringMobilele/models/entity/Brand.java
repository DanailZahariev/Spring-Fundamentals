package com.example.SpringMobilele.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "brands")
public class Brand extends BasicEntity {

    private String name;

    @OneToMany
    private List<Model> models;
}
