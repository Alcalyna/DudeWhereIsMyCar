
package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionInformationException;

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
        isValid(name, director);
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

    public void isValid(String name, Director director) {
        if(name == null || name.trim().equals("")) {
            throw new InvalidDivisionInformationException("Division requires a name!");
        }
        if(director == null) {
            throw new InvalidDivisionInformationException("Division requires a director!");
        }
    }

}
