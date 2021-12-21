package com.parkshark.dudewheremycar.api.parkinglots;

import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.ContactPerson;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLotCategory;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ParkingLotControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    void givenParkingLot_whenManagerCreatesParkingLot_thenParkingLotIsAdded(){
        ParkingLotDto createParkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", "3000", "myCity"))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername","switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivision(new Division("myDivision", "myDirector"))
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

    private boolean compareParkingLotDtos(ParkingLotDto actual, ParkingLotDto expected){
        if(!actual.getName().equals(expected.getName())){
            return false;
        }
        if(!actual.getAddress().equals(expected.getAddress())){
            return false;
        }
        if(!actual.getContactPerson().equals(expected.getContactPerson())){
            return false;
        }
        if(!actual.getParkingLotCategory().equals(expected.getParkingLotCategory())){
            return false;
        }
        if(!actual.getDivision().equals(expected.getDivision())){
            return false;
        }
        if(!(actual.getPricePerHour() == expected.getPricePerHour())){
            return false;
        }
        return true;
    }
}