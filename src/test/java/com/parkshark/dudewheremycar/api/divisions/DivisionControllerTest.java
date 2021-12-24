package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.CreateSubdivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionSubdivisionDto;
import com.parkshark.dudewheremycar.domain.divisions.DivisionSubdivision;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionSubdivisionInformationException;
import com.parkshark.dudewheremycar.repository.divisions.DivisionSubdivisionRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DivisionControllerTest {
    @Autowired
    DivisionSubdivisionRepository divisionSubdivisionRepository;

    @BeforeEach
    void init() {
        UUID id = UUID.fromString("fd68cfa3-2dd7-4082-8ac4-b734b667b82f");
        divisionSubdivisionRepository.removeDivisionSubdivisionByIdParent(id);
    }

    @Value("${server.port}")
    private int port;

    @Test
    void givenCreateDivisionDto_WhenUsingRestAssured_CreateAndReturnDivisionDto() {

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

    @Test
    void givenDatabaseWithData_WhenCallingGetAllDivisions_ReturnListOfDivisionDto() {
        List<DivisionDto> divisions = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .get("/divisions")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(List.class);

        Assertions.assertTrue(divisions.size() > 0);
    }

    @Test
    void createSubdivision() {
        CreateSubdivisionDto createSubdivisionDto = new CreateSubdivisionDto(UUID.fromString("e0e25604-2c5e-4688-b3eb-3c8fc45980c1"));

        DivisionSubdivisionDto divisionSubdivisionDto = RestAssured
                .given()
                .body(createSubdivisionDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .pathParam("id", "fd68cfa3-2dd7-4082-8ac4-b734b667b82f")
                .post("/divisions/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value())
                .extract()
                .as(DivisionSubdivisionDto.class);

        Assertions.assertEquals("fd68cfa3-2dd7-4082-8ac4-b734b667b82f", divisionSubdivisionDto.getIdParent().toString());
        Assertions.assertEquals("e0e25604-2c5e-4688-b3eb-3c8fc45980c1", divisionSubdivisionDto.getIdSubdivision().toString());
    }


    @Test
    void createSubdivisionWithNoExistingParent() {
        CreateSubdivisionDto createSubdivisionDto = new CreateSubdivisionDto(UUID.fromString("e0e25604-2c5e-4688-b3eb-3c8fc45980c1"));

        String exception = RestAssured
                .given()
                .body(createSubdivisionDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .pathParam("id", "fd68cfa3-2dd7-4082-8ac5-b734b667b82f")
                .post("/divisions/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .extract().path("message");

        Assertions.assertEquals("This division doesn't exist!", exception);
    }

    @Test
    void createSubdivisionWithNoExistingDivision() {
        CreateSubdivisionDto createSubdivisionDto = new CreateSubdivisionDto(UUID.fromString("e0e25604-2c5e-4688-b3eb-3c8fc45980c2"));

        String exception = RestAssured
                .given()
                .body(createSubdivisionDto)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .pathParam("id", "fd68cfa3-2dd7-4082-8ac4-b734b667b82f")
                .post("/divisions/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .extract().path("message");

        Assertions.assertEquals("This subdivision doesn't exist! Please create it before.", exception);
    }

    @Test
    void getDivisionDtoById() {
        DivisionDto divisionDto = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .pathParam("id", "fd68cfa3-2dd7-4082-8ac4-b734b667b82f")
                .get("/divisions/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .as(DivisionDto.class);

        Assertions.assertEquals("Switchbelly", divisionDto.getName());
        Assertions.assertEquals("Switchfully", divisionDto.getOriginalName());
    }

    @Test
    void getDivisionDtoByIdThatDoesNotExist() {
        String exception = RestAssured
                .given()
                .accept(JSON)
                .contentType(JSON)
                .when()
                .port(port)
                .pathParam("id", "fd68cfa3-2dd7-4082-8ac4-b734b667b789")
                .get("/divisions/{id}")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract().path("message");

        Assertions.assertEquals("This division doesn't exist!", exception);
    }
}