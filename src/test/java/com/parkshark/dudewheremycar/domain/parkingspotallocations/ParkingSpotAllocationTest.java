package com.parkshark.dudewheremycar.domain.parkingspotallocations;

import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidParkingSpotAllocationInformationException;
import com.parkshark.dudewheremycar.domain.information.*;
import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLotCategory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ParkingSpotAllocationTest {

    private static LicensePlate licensePlate;
    private static Member member;
    private static ParkingLot parkingLot;

    @BeforeAll
    static void setup() {
        licensePlate = new LicensePlate("111222", "LOL");

        member = Member.MemberBuilder.aMember()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0486162018")
                .withLicensePlate(licensePlate)
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        parkingLot = ParkingLot.ParkingLotBuilder.aParkingLot()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername", "switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build();
    }

    @Test
    void givenAParkingSpotAllocationWithNoMember_whenCreatingParkingLot_InvalidParkingSpotAllocationInformationExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidParkingSpotAllocationInformationException.class)
                .isThrownBy(() -> ParkingSpotAllocation.ParkingSpotAllocationBuilder.aParkingSpotAllocation()
                        .withParkingLot(parkingLot)
                        .withLicensePlate(licensePlate)
                        .build());
    }

    @Test
    void givenAParkingSpotAllocationWithNoParkingLot_whenCreatingParkingLot_InvalidParkingSpotAllocationInformationExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidParkingSpotAllocationInformationException.class)
                .isThrownBy(() -> ParkingSpotAllocation.ParkingSpotAllocationBuilder.aParkingSpotAllocation()
                        .withMember(member)
                        .withLicensePlate(licensePlate)
                        .build());
    }

    @Test
    void givenAParkingSpotAllocationWithNoLicensePlate_whenCreatingParkingLot_InvalidParkingSpotAllocationInformationExceptionIsThrown() {
        Assertions.assertThatExceptionOfType(InvalidParkingSpotAllocationInformationException.class)
                .isThrownBy(() -> ParkingSpotAllocation.ParkingSpotAllocationBuilder.aParkingSpotAllocation()
                        .withMember(member)
                        .withParkingLot(parkingLot)
                        .build());
    }
}