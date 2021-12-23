package com.parkshark.dudewheremycar.api.dtos.divisions;

import com.parkshark.dudewheremycar.domain.divisions.Director;

import java.util.UUID;

public class DivisionDto {
    private UUID id;
    private String name;
    private String originalName;
    private Director director;

    public DivisionDto(UUID id, String name, String originalName, Director director) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    public DivisionDto(String name, String originalName, Director director) {
        this(UUID.randomUUID(), name, originalName, director);
    }

    public DivisionDto() {
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

    public Director getDirector() {
        return director;
    }
}
