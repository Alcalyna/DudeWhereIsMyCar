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

}
