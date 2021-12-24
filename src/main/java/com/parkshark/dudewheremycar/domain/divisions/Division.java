
package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidDivisionInformationException;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_name")
    private String originalName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "director")
    private Director director;

    public Division(String name, String originalName, Director director) {
        this(UUID.randomUUID(), name, originalName, director);
    }

    public Division(UUID id, String name, String originalName, Director director) {
        isValid(name, director);
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    protected Division() {
    }

    public String getOriginalName() {
        return originalName;
    }

    public void isValid(String name, Director director) {
        if (name == null || name.trim().equals("")) {
            throw new InvalidDivisionInformationException("Division requires a name!");
        }
        if (director == null) {
            throw new InvalidDivisionInformationException("Division requires a director!");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Director getDirector() {
        return director;
    }
}
