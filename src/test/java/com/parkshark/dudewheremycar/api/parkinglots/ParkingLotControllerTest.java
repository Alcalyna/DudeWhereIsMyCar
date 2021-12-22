package com.parkshark.dudewheremycar.api.parkinglots;

import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.City;
import com.parkshark.dudewheremycar.domain.information.ContactPerson;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLotCategory;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ParkingLotControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    @Transactional
    void givenParkingLot_whenManagerCreatesParkingLot_thenParkingLotIsAdded(){
        ParkingLotDto createParkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build();

        //String authorization = "Basic " + Base64.getEncoder().encodeToString("admin@mail.com:adminpassword".getBytes());

        ParkingLotDto createdParkingLotDto =
                RestAssured
                        .given()
                        .body(createParkingLotDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/parkinglots")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ParkingLotDto.class);

        assertThat(compareParkingLotDtos(createdParkingLotDto,createParkingLotDto)).isTrue();
    }

    @Test
    @Transactional
    void givenIncompleteParkingLot_whenManagerCreatesParkingLot_thenBadRequestIsReturnedWithMessage(){
        ParkingLotDto createParkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                        "0123456789", "9876543210"))
                .withDivision(new Division("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build();

        //String authorization = "Basic " + Base64.getEncoder().encodeToString("admin@mail.com:adminpassword".getBytes());

        String responseMessage =
                RestAssured
                        .given()
                        .body(createParkingLotDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/parkinglots")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .extract().path("message");

        Assertions.assertThat(responseMessage).isEqualTo("A parking lot requires a strictly positive capacity");
    }

    private boolean compareParkingLotDtos(ParkingLotDto actual, ParkingLotDto expected){
        if(!actual.getName().equals(expected.getName())){
            return false;
        }
        if(!compareAddresses(actual.getAddress(), expected.getAddress())){
            return false;
        }
        if(!compareContactPerson(actual.getContactPerson(), expected.getContactPerson())){
            return false;
        }
        if(!actual.getParkingLotCategory().equals(expected.getParkingLotCategory())){
            return false;
        }
        if(!compareDivisions(actual.getDivision(), expected.getDivision())){
            return false;
        }
        if(!(actual.getPricePerHour() == expected.getPricePerHour())){
            return false;
        }
        return true;
    }

    private boolean compareDivisions(Division actual, Division expected) {
        if (!actual.getDirector().getFirstName().equals(expected.getDirector().getFirstName()))
            return false;
        if (!actual.getDirector().getLastName().equals(expected.getDirector().getLastName()))
            return false;
        if (!actual.getOriginalName().equals(expected.getOriginalName()))
            return false;
        if (!actual.getName().equals(expected.getName()))
            return false;
        return true;
    }

    private boolean compareContactPerson(ContactPerson actual, ContactPerson expected) {
        if (!actual.getEmailAddress().getDomain().equals(expected.getEmailAddress().getDomain()))
            return false;
        if (!actual.getEmailAddress().getUsername().equals(expected.getEmailAddress().getUsername()))
            return false;
        if (!actual.getMobileNumber().equals(expected.getMobileNumber()))
            return false;
        if (!actual.getPhoneNumber().equals(expected.getPhoneNumber()))
            return false;
        return true;
    }

    private boolean compareAddresses(Address actual, Address expected) {
        if (!actual.getStreetName().equals(expected.getStreetName()))
            return false;
        if (!actual.getStreetNumber().equals(expected.getStreetNumber()))
            return false;
        if (!actual.getCity().getZipCode().equals(expected.getCity().getZipCode()))
            return false;
        if (!actual.getCity().getName().equals(expected.getCity().getName()))
            return false;
        return true;
    }
}