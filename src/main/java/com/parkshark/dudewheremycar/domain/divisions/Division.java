package com.parkshark.dudewheremycar.domain.divisions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "divisions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Division {

    @Id
    @Column(name= "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name= "name")
    private String name;

    @Column(name= "original_name")
    private String originalName;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "director")
    private Director director;

    public Division(String name, String originalName, Director director) {
        this.name = name;
        this.originalName = originalName;
        this.director = director;
    }

    private Division() {
    }

    public String getName() {
        return name;
    }

    public Director getDirector() {
        return director;
    }

    public String getOriginalName() {
        return originalName;
    }
}
