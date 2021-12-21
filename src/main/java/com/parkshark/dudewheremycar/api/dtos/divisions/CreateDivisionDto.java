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
}
