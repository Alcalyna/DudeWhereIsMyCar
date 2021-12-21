package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//@Sql("/script.sql")
class DivisionControllerTest {

    @Value("${server.port}")
    private int port;

    @Test
    void createDivision() {

        CreateDivisionDto createDivisionDto = new CreateDivisionDto()
                .setName("Garden Dolphin")
                .setOriginalName("Park Shark")
                .setDirectorId(UUID.fromString("66241ef1-c725-4e0a-afcc-02658be40afc"));

        DivisionDto divisionDto = RestAssured
                .given()
                .body(createDivisionDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .post("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(DivisionDto.class);

        Assertions.assertTrue(!divisionDto.getId().toString().isBlank());
        Assertions.assertEquals("Garden Dolphin", divisionDto.getName());
        Assertions.assertEquals("Park Shark", divisionDto.getOriginalName());
    }
}