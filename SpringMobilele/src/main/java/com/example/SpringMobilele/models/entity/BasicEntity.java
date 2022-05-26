package com.example.SpringMobilele.model.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created;

    @Column(nullable = false)
    private LocalDateTime modified;


    public Long getId() {
        return id;
    }

    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public BasicEntity setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public BasicEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
