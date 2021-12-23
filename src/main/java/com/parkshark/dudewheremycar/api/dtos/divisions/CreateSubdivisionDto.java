package com.parkshark.dudewheremycar.api.dtos.divisions;

import java.util.UUID;

public class CreateSubdivisionDto {
    UUID subdivisionId;

    public UUID getSubdivisionId() {
        return subdivisionId;
    }

    public CreateSubdivisionDto(UUID subdivisionId) {
        this.subdivisionId = subdivisionId;
    }

    public CreateSubdivisionDto() {
    }
}