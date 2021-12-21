package com.parkshark.dudewheremycar.domain.information;

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

    @ManyToOne
    @JoinColumn(name = "zip")
    private City city;

    public Address(String streetName, String streetNumber, City city) {
        this.id = UUID.randomUUID();
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.city = city;
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
