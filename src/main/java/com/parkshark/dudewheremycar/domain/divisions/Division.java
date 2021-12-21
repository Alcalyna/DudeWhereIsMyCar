package com.parkshark.dudewheremycar.domain.divisions;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "DIVISIONS")
public class Division {

    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @ManyToOne
    @JoinColumn(name = "DIRECTOR")

    @Transient
    private Director director;

    public void setDirector(Director director) {
        this.director = director;
    }

    public Director getDirector() {
        return director;
    }

    public Division() {
    }

    public Division(String name, String originalName, Director director) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }
}
