package com.parkshark.dudewheremycar.service.parkinglots;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;

import java.util.UUID;

@JsonDeserialize(builder= ParkingLotSummaryDto.ParkingLotSummaryDtoBuilder.class)
public class ParkingLotSummaryDto {

    private final UUID id;
    private final String name;
    private final int maxCapacity;
    private final EmailAddress contactPersonEmail;
    private final String contactPersonPhone;
    private final String contactPersonMobile;

    public ParkingLotSummaryDto(ParkingLotSummaryDtoBuilder parkingLotSummaryDtoBuilder) {
        this.id = parkingLotSummaryDtoBuilder.id;
        this.name = parkingLotSummaryDtoBuilder.name;
        this.maxCapacity = parkingLotSummaryDtoBuilder.maxCapacity;
        this.contactPersonEmail = parkingLotSummaryDtoBuilder.contactPersonEmail;
        this.contactPersonPhone = parkingLotSummaryDtoBuilder.contactPersonPhone;
        this.contactPersonMobile = parkingLotSummaryDtoBuilder.contactPersonMobile;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public EmailAddress getContactPersonEmail() {
        return contactPersonEmail;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public String getContactPersonMobile() {
        return contactPersonMobile;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static final class ParkingLotSummaryDtoBuilder {
        private UUID id;
        private String name;
        private int maxCapacity;
        private EmailAddress contactPersonEmail;
        private String contactPersonPhone;
        private String contactPersonMobile;

        private ParkingLotSummaryDtoBuilder() {
        }

        public static ParkingLotSummaryDtoBuilder aParkingLotSummaryDto() {
            return new ParkingLotSummaryDtoBuilder();
        }


        public ParkingLotSummaryDto build() {
            return new ParkingLotSummaryDto(this);
        }

        public ParkingLotSummaryDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ParkingLotSummaryDtoBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ParkingLotSummaryDtoBuilder withMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
            return this;
        }

        public ParkingLotSummaryDtoBuilder withContactPersonEmail(EmailAddress contactPersonEmail) {
            this.contactPersonEmail = contactPersonEmail;
            return this;
        }

        public ParkingLotSummaryDtoBuilder withContactPersonPhone(String contactPersonPhone) {
            this.contactPersonPhone = contactPersonPhone;
            return this;
        }

        public ParkingLotSummaryDtoBuilder withContactPersonMobile(String contactPersonMobile) {
            this.contactPersonMobile = contactPersonMobile;
            return this;
        }

    }
}
