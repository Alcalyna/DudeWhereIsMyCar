package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.members.Member;

import javax.persistence.*;

@Entity
@Table(name = "license_plates")
public class LicensePlate {

    @Id
    @Column(name = "license_plate")
    private String licensePlateNumber;

    @Column(name = "country")
    private String country;

    public LicensePlate() {

    }

    public LicensePlate(String licensePlateNumber, String country) {
        this.licensePlateNumber = licensePlateNumber;
        this.country = country;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public String getCountry() {
        return country;
    }
}
