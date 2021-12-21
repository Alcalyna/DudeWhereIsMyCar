package com.parkshark.dudewheremycar.domain.divisions;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name= "divisions")
public class Division {

    @Id
    private UUID id;

    @Column(name= "name")
    private final String name;

    @Column(name= "original_name")
    private final String originalName;

    public Division(String name, String originalName) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.originalName = originalName;
    }

    public String getName() {
        return name;
    }

    public String getOriginalName() {
        return originalName;
    }
}
