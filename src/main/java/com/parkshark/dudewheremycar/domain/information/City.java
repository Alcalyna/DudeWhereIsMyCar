package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidCityInformationException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "zip")
    private String zipCode;

    @Column(name = "city")
    private String name;

    public City(String zipCode, String name) {
        validateCityInformation(zipCode,name);
        this.zipCode = zipCode;
        this.name = name;
    }

    private void validateCityInformation(String zipCode, String name) {
        if(zipCode == null){
            throw new InvalidCityInformationException("A city requires a zip code");
        }
        if(name == null){
            throw new InvalidCityInformationException("A city requires a name");
        }
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
