package com.parkshark.dudewheremycar.api.dtos.divisions;

import java.util.UUID;

public class CreateDivisionDto {
    private String name;
    private String originalName;
    private UUID directorId;

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public UUID getDirectorId() {
        return directorId;
    }

    public CreateDivisionDto setName(String name) {
        this.name = name;
        return this;
    }

    public CreateDivisionDto setOriginalName(String originalName) {
        this.originalName = originalName;
        return this;
    }

    public CreateDivisionDto setDirectorId(UUID directorId) {
        this.directorId = directorId;
        return this;
    }
}
