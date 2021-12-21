package com.parkshark.dudewheremycar.domain.parkinglots;

import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.ContactPerson;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parking_lots")
public class ParkingLot {

    @Id
    @Column(name= "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private ParkingLotCategory parkingLotCategory;

    @Column(name = "max_capacity")
    private int maxCapacity;

    @ManyToOne
    @JoinColumn(name = "contact_person")
    private ContactPerson contactPerson;

    @OneToOne
    @JoinColumn(name = "address")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "division")
    private Division division;

    @Column(name = "price_per_hour")
    private double pricePerHour;

    private ParkingLot(ParkingLotBuilder parkingLotBuilder) {
        this.id = UUID.randomUUID();
        this.name = parkingLotBuilder.name;
        this.address = parkingLotBuilder.address;
        this.parkingLotCategory = parkingLotBuilder.parkingLotCategory;
        this.contactPerson = parkingLotBuilder.contactPerson;
        this.maxCapacity = parkingLotBuilder.maxCapacity;
        this.division = parkingLotBuilder.division;
        this.pricePerHour = parkingLotBuilder.pricePerHour;
    }

    protected ParkingLot() {}

    public UUID getId() { return id; }

    public String getName() {
        return name;
    }

    public ParkingLotCategory getParkingLotCategory() {
        return parkingLotCategory;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public Address getAddress() {
        return address;
    }

    public Division getDivision() {
        return division;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public static final class ParkingLotBuilder {
        private String name;
        private ParkingLotCategory parkingLotCategory;
        private int maxCapacity;
        private ContactPerson contactPerson;
        private Address address;
        private Division division;
        private double pricePerHour;

        private ParkingLotBuilder() {
        }

        public static ParkingLotBuilder aParkingLot() {
            return new ParkingLotBuilder();
        }

        public ParkingLotBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ParkingLotBuilder withParkingLotCategory(ParkingLotCategory parkingLotCategory) {
            this.parkingLotCategory = parkingLotCategory;
            return this;
        }

        public ParkingLotBuilder withMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public ParkingLotBuilder withContactPerson(ContactPerson contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public ParkingLotBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public ParkingLotBuilder withDivision(Division division) {
            this.division = division;
            return this;
        }

        public ParkingLotBuilder withPricePerHour(double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(this);
        }
    }
}
