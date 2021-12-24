package com.parkshark.dudewheremycar.api.dtos.divisions;

import java.util.UUID;

public class DivisionSubdivisionDto {
    private UUID idParent;
    private UUID idSubdivision;

    public DivisionSubdivisionDto(UUID idParent, UUID idSubdivision) {
        this.idParent = idParent;
        this.idSubdivision = idSubdivision;
    }

    public UUID getIdParent() {
        return idParent;
    }

    public UUID getIdSubdivision() {
        return idSubdivision;
    }
}
