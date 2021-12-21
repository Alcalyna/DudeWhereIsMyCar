package com.parkshark.dudewheremycar.domain.divisions;

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
