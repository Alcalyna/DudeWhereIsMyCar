package com.parkshark.dudewheremycar.domain.parkingspotallocations;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidParkingSpotAllocationInformationException;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;
import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "allocations")
public class ParkingSpotAllocation {

    @Id
    @Column
    private UUID id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "license_plate")
    private LicensePlate licensePlate;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "stop_time")
    private LocalDateTime stopTime;

    public ParkingSpotAllocation(ParkingSpotAllocationBuilder parkingSpotAllocationBuilder) {
        validateParkingSpotAllocation(parkingSpotAllocationBuilder);
        this.id = UUID.randomUUID();
        this.member = parkingSpotAllocationBuilder.member;
        this.parkingLot = parkingSpotAllocationBuilder.parkingLot;
        this.licensePlate = parkingSpotAllocationBuilder.licensePlate;
        startTime = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
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

    public void stopAllocation(){
        this.stopTime = LocalDateTime.now();
    }

    private void validateParkingSpotAllocation(ParkingSpotAllocationBuilder parkingSpotAllocationBuilder) {
        if(parkingSpotAllocationBuilder.member == null){
            throw new InvalidParkingSpotAllocationInformationException("A parking spot allocation requires a member");
        }
        if(parkingSpotAllocationBuilder.parkingLot == null){
            throw new InvalidParkingSpotAllocationInformationException("A parking spot allocation requires a parking lot");
        }
        if(parkingSpotAllocationBuilder.licensePlate == null){
            throw new InvalidParkingSpotAllocationInformationException("A parking spot allocation requires a license plate");
        }
    }

    private ParkingSpotAllocation(){}


    public static final class ParkingSpotAllocationBuilder {
        private Member member;
        private ParkingLot parkingLot;
        private LicensePlate licensePlate;

        private ParkingSpotAllocationBuilder() {
        }

        public static ParkingSpotAllocationBuilder aParkingSpotAllocation() {
            return new ParkingSpotAllocationBuilder();
        }

        public ParkingSpotAllocationBuilder withMember(Member member) {
            this.member = member;
            return this;
        }

        public ParkingSpotAllocationBuilder withParkingLot(ParkingLot parkingLot) {
            this.parkingLot = parkingLot;
            return this;
        }

        public ParkingSpotAllocationBuilder withLicensePlate(LicensePlate licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public ParkingSpotAllocation build() {
            return new ParkingSpotAllocation(this);
        }
    }
}
