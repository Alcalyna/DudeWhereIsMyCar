package com.parkshark.dudewheremycar.api.members;

import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.City;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("test")
class MemberControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    @Transactional
    void givenMemberDto_whenMemberRegisters_thenMemberIsAdded_andMemberDtoIsReturned(){
        MemberDto createMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0486162018")
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        MemberDto createdMemberDto =
                RestAssured
                        .given()
                        .body(createMemberDto)
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

        assertThat(compareMemberDtos(createdMemberDto,createMemberDto)).isTrue();
    }

    @Test
    @Transactional
    void givenIncompleteMemberDto_whenMemberRegisters_thenMemberIsAdded_andMemberDtoIsReturned(){
        MemberDto createMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withMobileNumber("0486162018")
                .withEmailAddress(new EmailAddress("jan", "test"))
                .build();

        String responseMessage =
                RestAssured
                        .given()
                        .body(createMemberDto)
                        .accept(JSON)
                        .contentType(JSON)
                        .when()
                        .port(port)
                        .post("/members")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .extract().path("message");

        Assertions.assertEquals(responseMessage, "A member requires a phone number");
    }


    private boolean compareMemberDtos(MemberDto actual, MemberDto expected) {
        if (!actual.getFirstName().equals(expected.getFirstName())) {
            return false;
        }
        if (!actual.getLastName().equals(expected.getLastName())) {
            return false;
        }
        if (!actual.getAddress().getCity().getName().equals(expected.getAddress().getCity().getName())) {
            return false;
        }
        if (!compareAddresses(actual.getAddress(), expected.getAddress())){
            return false;
        }
        if (!actual.getMobileNumber().equals(expected.getMobileNumber())) {
            return false;
        }
        return actual.getPhoneNumber().equals(expected.getPhoneNumber());
    }

    private boolean compareAddresses(Address actual, Address expected) {
        if (!actual.getStreetName().equals(expected.getStreetName()))
            return false;
        if (!actual.getStreetNumber().equals(expected.getStreetNumber()))
            return false;
        if (!actual.getCity().getZipCode().equals(expected.getCity().getZipCode()))
            return false;
        return actual.getCity().getName().equals(expected.getCity().getName());
    }

}