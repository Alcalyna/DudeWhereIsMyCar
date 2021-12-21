package com.parkshark.dudewheremycar.domain.divisions;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name= "divisions")
public class Division {

    private final String name;
    private final String director;

    public Division(String name, String director) {
        this.name = name;
        this.director = director;
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }
}
