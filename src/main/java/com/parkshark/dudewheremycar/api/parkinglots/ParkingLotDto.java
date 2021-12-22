package com.parkshark.dudewheremycar.api.parkinglots;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.ContactPerson;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLotCategory;

import java.util.UUID;

@JsonDeserialize(builder = ParkingLotDto.ParkingLotDtoBuilder.class)
public class ParkingLotDto {

    private final UUID id;
    private final String name;
    private final ParkingLotCategory parkingLotCategory;
    private final int maxCapacity;
    private final ContactPerson contactPerson;
    private final Address address;
    private final Division division;
    private final double pricePerHour;

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

    public UUID getId() {
        return id;
    }

    private ParkingLotDto(ParkingLotDtoBuilder parkingLotDtoBuilder) {
        this.id = parkingLotDtoBuilder.id;
        this.name = parkingLotDtoBuilder.name;
        this.address = parkingLotDtoBuilder.address;
        this.parkingLotCategory = parkingLotDtoBuilder.parkingLotCategory;
        this.contactPerson = parkingLotDtoBuilder.contactPerson;
        this.maxCapacity = parkingLotDtoBuilder.maxCapacity;
        this.division = parkingLotDtoBuilder.division;
        this.pricePerHour = parkingLotDtoBuilder.pricePerHour;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static final class ParkingLotDtoBuilder {
        private UUID id;
        private String name;
        private ParkingLotCategory parkingLotCategory;
        private int maxCapacity;
        private ContactPerson contactPerson;
        private Address address;
        private Division division;
        private double pricePerHour;

        private ParkingLotDtoBuilder() {
        }

        public static ParkingLotDtoBuilder aParkingLotDto() {
            return new ParkingLotDtoBuilder();
        }

        public ParkingLotDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ParkingLotDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ParkingLotDtoBuilder withParkingLotCategory(ParkingLotCategory parkingLotCategory) {
            this.parkingLotCategory = parkingLotCategory;
            return this;
        }

        public ParkingLotDtoBuilder withMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public ParkingLotDtoBuilder withContactPerson(ContactPerson contactPerson) {
            this.contactPerson = contactPerson;
            return this;
        }

        public ParkingLotDtoBuilder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public ParkingLotDtoBuilder withDivision(Division division) {
            this.division = division;
            return this;
        }

        public ParkingLotDtoBuilder withPricePerHour(double pricePerHour) {
            this.pricePerHour = pricePerHour;
            return this;
        }

        public ParkingLotDto build() {
            return new ParkingLotDto(this);
        }
    }
}
