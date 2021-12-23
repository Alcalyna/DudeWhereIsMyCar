package com.parkshark.dudewheremycar.service.allocations;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;
import com.parkshark.dudewheremycar.service.members.MemberDto;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotDto;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonDeserialize(builder = ParkingSpotAllocationDto.ParkingSpotAllocationDtoBuilder.class)
public class ParkingSpotAllocationDto {

    private final UUID id;
    private final MemberDto memberDto;
    private final ParkingLotDto parkingLotDto;
    private final LicensePlate licensePlate;
    private final LocalDateTime startTime;
    private final LocalDateTime stopTime;

    public ParkingSpotAllocationDto(ParkingSpotAllocationDtoBuilder parkingSpotAllocationDtoBuilder){
        this.id = parkingSpotAllocationDtoBuilder.id;
        this.memberDto = parkingSpotAllocationDtoBuilder.memberDto;
        this.parkingLotDto = parkingSpotAllocationDtoBuilder.parkingLotDto;
        this.licensePlate = parkingSpotAllocationDtoBuilder.licensePlate;
        this.startTime = parkingSpotAllocationDtoBuilder.startTime;
        this.stopTime = parkingSpotAllocationDtoBuilder.stopTime;
    }

    public UUID getId() {
        return id;
    }

    public MemberDto getMemberDto() {
        return memberDto;
    }

    public ParkingLotDto getParkingLotDto() {
        return parkingLotDto;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    @JsonPOJOBuilder(withPrefix = "with")
    public static final class ParkingSpotAllocationDtoBuilder {
        private UUID id;
        private MemberDto memberDto;
        private ParkingLotDto parkingLotDto;
        private LicensePlate licensePlate;
        private LocalDateTime startTime;
        private LocalDateTime stopTime;

        private ParkingSpotAllocationDtoBuilder() {
        }

        public static ParkingSpotAllocationDtoBuilder aParkingSpotAllocationDto() {
            return new ParkingSpotAllocationDtoBuilder();
        }

        public ParkingSpotAllocationDtoBuilder withId(UUID id) {
            this.id = id;
            return this;
        }

        public ParkingSpotAllocationDtoBuilder withMemberDto(MemberDto memberDto) {
            this.memberDto = memberDto;
            return this;
        }

        public ParkingSpotAllocationDtoBuilder withParkingLotDto(ParkingLotDto parkingLotDto) {
            this.parkingLotDto = parkingLotDto;
            return this;
        }

        public ParkingSpotAllocationDtoBuilder withLicensePlate(LicensePlate licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public ParkingSpotAllocationDtoBuilder withStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public ParkingSpotAllocationDtoBuilder withStopTime(LocalDateTime stopTime) {
            this.stopTime = stopTime;
            return this;
        }

        public ParkingSpotAllocationDto build() {
            return new ParkingSpotAllocationDto(this);
        }
    }
}
