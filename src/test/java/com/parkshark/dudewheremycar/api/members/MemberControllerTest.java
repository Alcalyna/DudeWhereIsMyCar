package com.parkshark.dudewheremycar.api.members;

import com.parkshark.dudewheremycar.domain.information.Address;
import com.parkshark.dudewheremycar.domain.information.City;
import com.parkshark.dudewheremycar.domain.information.EmailAddress;
import com.parkshark.dudewheremycar.domain.information.LicensePlate;
import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.domain.members.MembershipLevel;
import com.parkshark.dudewheremycar.service.members.MemberDto;
import com.parkshark.dudewheremycar.service.members.MemberSummaryDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@ActiveProfiles("test")
class MemberControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    @Transactional
    void givenMemberDto_whenMemberRegisters_thenMemberIsAdded_andMemberDtoIsReturned() {
        MemberDto createMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("TheTestMan")
                .withAddress(new Address("TestStreet", "8", new City("9000", "Gent")))
                .withPhoneNumber("092530082")
                .withMobileNumber("0486162018")
                .withLicensePlate(new LicensePlate("ABC123", "Belgium"))
                .withEmailAddress(new EmailAddress("jan", "test"))
                .withMembershipLevel(MembershipLevel.SILVER)
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

        assertThat(compareMemberDtos(createdMemberDto, createMemberDto)).isTrue();
    }

    @Test
    @Transactional
    void givenIncompleteMemberDto_whenMemberRegisters_thenMemberIsAdded_andMemberDtoIsReturned() {
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

    @Test
    @Transactional
    void givenTwoMembers_whenManagerGetsAllMembers_thenTwoMemberDtosAreReturned() {

        MemberDto createFirstMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Jan")
                .withLastName("Janssen")
                .withAddress(new Address("JanStraat", "8", new City("9000", "Gent")))
                .withMobileNumber("0486162018")
                .withEmailAddress(new EmailAddress("jan", "janssens"))
                .withLicensePlate(new LicensePlate("ABC123", "Belgium"))
                .withPhoneNumber("092530082")
                .build();

        MemberDto createSecondMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName("Ruben")
                .withLastName("Rubens")
                .withAddress(new Address("RubenStraat", "5", new City("3590", "Diepenbeek")))
                .withMobileNumber("0484482978")
                .withLicensePlate(new LicensePlate("XYZ987", "Netherlands"))
                .withEmailAddress(new EmailAddress("ruben", "rubens"))
                .withPhoneNumber("012345678")
                .build();


        MemberDto createdFirstMemberDto = RestAssured
                .given()
                .body(createFirstMemberDto)
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

        MemberDto createdSecondMemberDto = RestAssured
                .given()
                .body(createSecondMemberDto)
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


        List<MemberSummaryDto> memberSummaryDtos = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .get("/members")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .jsonPath()
                .getList(".", MemberSummaryDto.class);

        assertThat(listContainsMemberSummaryDto(memberSummaryDtos, createdFirstMemberDto)).isTrue();
        assertThat(listContainsMemberSummaryDto(memberSummaryDtos, createdSecondMemberDto)).isTrue();
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
        if (!compareAddresses(actual.getAddress(), expected.getAddress())) {
            return false;
        }
        if (!actual.getMobileNumber().equals(expected.getMobileNumber())) {
            return false;
        }
        if (!(actual.getMembershipLevel().getMonthlyCost() == (expected.getMembershipLevel().getMonthlyCost()))) {
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

    private boolean listContainsMemberSummaryDto(List<MemberSummaryDto> memberSummaryDtos, MemberDto dtoToCompare) {
        for (MemberSummaryDto memberSummary : memberSummaryDtos) {
            boolean dtosAreEqual = true;
            if (!memberSummary.getMemberId().equals(dtoToCompare.getMemberId())) {
                dtosAreEqual = false;
            }
            if (!memberSummary.getFirstName().equals(dtoToCompare.getFirstName())) {
                dtosAreEqual = false;
            }
            if (!(memberSummary.getLastName().equals(dtoToCompare.getLastName()))) {
                dtosAreEqual = false;
            }
            if (!memberSummary.getRegistrationDate().equals(dtoToCompare.getRegistrationDate())) {
                dtosAreEqual = false;
            }
            if (!memberSummary.getLicensePlateNumber().equals(dtoToCompare.getLicensePlate().getLicensePlateNumber())) {
                dtosAreEqual = false;
            }
            if (!memberSummary.getPhoneNumber().equals(dtoToCompare.getPhoneNumber())) {
                dtosAreEqual = false;
            }
            if (!memberSummary.getEmailAddress().getUsername().equals(dtoToCompare.getEmailAddress().getUsername())) {
                dtosAreEqual = false;
            }
            if (dtosAreEqual)
                return true;
        }
        return false;
    }
}