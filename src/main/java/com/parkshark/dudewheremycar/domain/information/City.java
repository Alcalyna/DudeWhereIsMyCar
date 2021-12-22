package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class City {

    @Id
    @Column(name = "zip")
    private String zipCode;

    @Column(name = "city")
    private String name;

    public City(String zipCode, String name) {
        this.zipCode = zipCode;
        this.name = name;
    }

    private City() {
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }
}
