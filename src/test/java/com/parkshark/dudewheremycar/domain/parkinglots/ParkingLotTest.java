package com.parkshark.dudewheremycar.domain.parkinglots;

import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidParkingLotInformationException;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.City;
import com.parkshark.dudewheremycar.domain.information.ContactPerson;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotTest {

    @Test
    void givenAParkingLotWithNoName_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
        ParkingLot.ParkingLotBuilder.aParkingLot()
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build());
    }

    @Test
    void givenAParkingLotWithNoAddress_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(250)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(55.25)
                                .build());
    }

    @Test
    void givenAParkingLotWithNoCategory_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(250)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(55.25)
                                .build());
    }

    @Test
    void givenAParkingLotWithNoContactPerson_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withMaxCapacity(250)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(55.25)
                                .build());
    }

    @Test
    void givenAParkingLotWithZeroMaxCapacity_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(0)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(55.25)
                                .build());
    }

    @Test
    void givenAParkingLotWithNegativeMaxCapacity_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(-1)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(55.25)
                                .build());
    }
    @Test
    void givenAParkingLotWithNoDivision_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(250)
                                .withPricePerHour(55.25)
                                .build());
    }
    @Test
    void givenAParkingLotWithNegativePricePerHour_whenCreatingParkingLot_InvalidParkingLotInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidParkingLotInformationException.class)
                .isThrownBy(() ->
                        ParkingLot.ParkingLotBuilder.aParkingLot()
                                .withName("parkinglot1")
                                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                        "0123456789", "9876543210"))
                                .withMaxCapacity(250)
                                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                                .withPricePerHour(-1)
                                .build());
    }
}