package com.parkshark.dudewheremycar.api.allocations;

import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.domain.divisions.Director;
import com.parkshark.dudewheremycar.domain.information.*;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLotCategory;
import com.parkshark.dudewheremycar.service.allocations.ParkingSpotAllocationDto;
import com.parkshark.dudewheremycar.service.members.MemberDto;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotDto;
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
class ParkingSpotAllocationControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    @Transactional
    void givenParkingSpotAllocation_whenCreatingParkingSpotAllocation_thenParkingSpotAllocationIsAdded() {
        LicensePlate licensePlate = new LicensePlate("124ABC", "Belgium");

        MemberDto memberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0486162018")
                .withLicensePlate(licensePlate)
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        ParkingLotDto parkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername", "switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivisionDto(new DivisionDto("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build();

        //register member with license plate
        MemberDto createdMemberDto = RestAssured
                .given()
                .body(memberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(MemberDto.class);

        //register parking lot
        ParkingLotDto createdParkingLotDto = RestAssured
                .given()
                .body(parkingLotDto)
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

        ParkingSpotAllocationDto createParkingSpotAllocationDto = ParkingSpotAllocationDto.
                ParkingSpotAllocationDtoBuilder.aParkingSpotAllocationDto()
                .withMemberDto(createdMemberDto)
                .withParkingLotDto(createdParkingLotDto)
                .withLicensePlate(licensePlate)
                .build();

        //allocate parking spot with member, parking lot and license plate
        ParkingSpotAllocationDto createdParkingSpotAllocationDto =
                RestAssured
                        .given()
                        .body(createParkingSpotAllocationDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/allocations")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ParkingSpotAllocationDto.class);

        assertThat(createdParkingSpotAllocationDto.getId()).isNotNull();
        assertThat(compareParkingSpotAllocations(createdParkingSpotAllocationDto,createParkingSpotAllocationDto)).isTrue();
    }

    private boolean compareParkingSpotAllocations(ParkingSpotAllocationDto createdParkingSpotAllocationDto,
                                                  ParkingSpotAllocationDto createParkingSpotAllocationDto) {
        if(!createdParkingSpotAllocationDto.getMemberDto().getMemberId().
                equals(createParkingSpotAllocationDto.getMemberDto().getMemberId())){
            return false;
        }
        if(!createdParkingSpotAllocationDto.getParkingLotDto().getId().
                equals(createParkingSpotAllocationDto.getParkingLotDto().getId())){
            return false;
        }
        if(!createdParkingSpotAllocationDto.getLicensePlate().getLicensePlateNumber().
                equals(createParkingSpotAllocationDto.getLicensePlate().getLicensePlateNumber())){
            return false;
        }
        return true;
    }

    @Test
    @Transactional
    void givenTwoParkingSpotAllocations_whenCreatingBothParkingSpotAllocationWithSameMember_thenSecondOneGivesABadRequestWithMessage() {
        LicensePlate licensePlate = new LicensePlate("124ABC", "Belgium");

        MemberDto memberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0486162018")
                .withLicensePlate(licensePlate)
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        ParkingLotDto parkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withName("parkinglot1")
                .withParkingLotCategory(ParkingLotCategory.ABOVEGROUND)
                .withAddress(new Address("myStreetName", "69", new City("3000", "myCity")))
                .withContactPerson(new ContactPerson(new EmailAddress("myUsername", "switch.com"),
                        "0123456789", "9876543210"))
                .withMaxCapacity(250)
                .withDivisionDto(new DivisionDto("myDivision", "myOriginalDivision", new Director("firstName", "last")))
                .withPricePerHour(55.25)
                .build();

        //register member with license plate
        MemberDto createdMemberDto = RestAssured
                .given()
                .body(memberDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(MemberDto.class);

        //register parking lot
        ParkingLotDto createdParkingLotDto = RestAssured
                .given()
                .body(parkingLotDto)
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

        ParkingSpotAllocationDto createParkingSpotAllocationDto1 = ParkingSpotAllocationDto.
                ParkingSpotAllocationDtoBuilder.aParkingSpotAllocationDto()
                .withMemberDto(createdMemberDto)
                .withParkingLotDto(createdParkingLotDto)
                .withLicensePlate(licensePlate)
                .build();

        //allocate parking spot with member, parking lot and license plate
        ParkingSpotAllocationDto createdParkingSpotAllocationDto =
                RestAssured
                        .given()
                        .body(createParkingSpotAllocationDto1)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/allocations")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ParkingSpotAllocationDto.class);

        ParkingSpotAllocationDto createParkingSpotAllocationDto2 = ParkingSpotAllocationDto.
                ParkingSpotAllocationDtoBuilder.aParkingSpotAllocationDto()
                .withMemberDto(createdMemberDto)
                .withParkingLotDto(createdParkingLotDto)
                .withLicensePlate(licensePlate)
                .build();

        String responseMessage =
                RestAssured
                        .given()
                        .body(createParkingSpotAllocationDto2)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/allocations")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .extract().path("message");

        Assertions.assertThat(responseMessage).isEqualTo("The member with id: " + createdMemberDto.getMemberId()
                + " already has currently active allocation");

    }
}