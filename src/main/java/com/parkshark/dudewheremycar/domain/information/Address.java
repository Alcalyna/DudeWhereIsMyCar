package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
