package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidAddressInformationException;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name= "id")
    private UUID id;

    @Column(name = "street_name")
    private String streetName;

    @Column(name = "street_number")
    private String streetNumber;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "zip")
    private City city;

    public Address(String streetName, String streetNumber, City city) {
        validateAddressInformation(streetName,streetNumber,city);
        this.id = UUID.randomUUID();
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
    }

    private void validateAddressInformation(String streetName, String streetNumber, City city) {
        if(streetName == null){
            throw new InvalidAddressInformationException("An address requires a street name");
        }
        if(streetNumber == null){
            throw new InvalidAddressInformationException("An address requires a street number");
        }
        if(city == null){
            throw new InvalidAddressInformationException("An address requires a city");
        }
    }

    private Address() {}

    public UUID getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public City getCity() {
        return city;
    }

}
