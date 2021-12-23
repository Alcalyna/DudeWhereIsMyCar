package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionSubdivisionInformationException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "has_subdivision")
public class DivisionSubdivision {
    @Column(name = "id_parent")
    private UUID idParent;

    @Id
    @Column(name = "id_subdivision")
    private UUID idSubdivision;

    public DivisionSubdivision(UUID idParent, UUID idSubdivision) {
        isValid(idParent, idSubdivision);
        this.idParent = idParent;
        this.idSubdivision = idSubdivision;
    }

    protected DivisionSubdivision() {
    }

    public UUID getIdParent() {
        return idParent;
    }

    public UUID getIdSubdivision() {
        return idSubdivision;
    }

    public void isValid(UUID idParent, UUID idSubdivision) {
        if(idParent.equals(idSubdivision)) {
            throw new InvalidDivisionSubdivisionInformationException("A division can't be its own subdivision!");
        }
        if(idParent == null) {
            throw new InvalidDivisionSubdivisionInformationException("The id parent can't be null!");
        }
        if(idSubdivision == null) {
            throw new InvalidDivisionSubdivisionInformationException("The id subdivision can't be null!");
        }
    }

}
