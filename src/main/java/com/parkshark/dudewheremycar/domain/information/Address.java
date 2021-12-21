package com.parkshark.dudewheremycar.domain.information;

import java.util.UUID;

public class Address {

    private final UUID id;
    private final String streetName;
    private final String streetNumber;
    private final String zipCode;
    private final String city;

    public Address(String streetName, String streetNumber, String zipCode, String city) {
        this.id = UUID.randomUUID();
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipCode = zipCode;
        this.city = city;
    }
}
