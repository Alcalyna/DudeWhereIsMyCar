package com.parkshark.dudewheremycar.domain.divisions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidDirectorInformationException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "DIRECTORS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Director {
    @Id
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    private Director() {
    }

    public Director(String firstName, String lastName) {
        isValid(firstName, lastName);
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return id;
    }

    private void isValid(String firstName, String lastName) {
        if(firstName == null || firstName.trim().equals("")) {
            throw new InvalidDirectorInformationException("Director requires a first name!");
        }
        if(lastName == null || lastName.trim().equals("")) {
            throw new InvalidDirectorInformationException("Director requires a last name!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}

