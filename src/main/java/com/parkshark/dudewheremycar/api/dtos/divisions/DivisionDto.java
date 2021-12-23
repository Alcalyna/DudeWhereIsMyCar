package com.parkshark.dudewheremycar.api.dtos.divisions;

import com.parkshark.dudewheremycar.domain.divisions.Director;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DivisionDto {
    private UUID id;
    private String name;
    private String originalName;
    private Director director;
    private List<DivisionDto> divisions;

    public DivisionDto(UUID id, String name, String originalName, Director director) {
        this.id = id;
        this.name = name;
        this.originalName = originalName;
        this.director = director;
        this.divisions = new ArrayList<>();
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

    public DivisionDto setDivisions(List<DivisionDto> divisions) {
        this.divisions = divisions;
        return this;
    }

    public List<DivisionDto> getDivisions() {
        return divisions;
    }
}
