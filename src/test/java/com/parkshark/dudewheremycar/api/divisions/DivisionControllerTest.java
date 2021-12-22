package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static io.restassured.http.ContentType.JSON;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DivisionControllerTest {
    String token;

    @BeforeEach
    void setUp() {
        token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICIzU0pncWUzZ2w3WGdIbldIRHNBMUN2M0lWSDVoYm81ekNHZ1BVV0xSNFk4In0.eyJleHAiOjE2NDAxNzkxNDksImlhdCI6MTY0MDE3NTU0OSwianRpIjoiYzRjMTczODEtZDBmNi00YjZiLWEyYmYtNWNlYmFjYWM1YzFjIiwiaXNzIjoiaHR0cHM6Ly9rZXljbG9hay5zd2l0Y2hmdWxseS5jb20vYXV0aC9yZWFsbXMvamF2YS1vY3QtMjAyMSIsImF1ZCI6WyJhY2NvdW50Iiwib3JkZXIiXSwic3ViIjoiYjhiMTU0OTYtMDBkNS00ZjI4LWE5MjItOTFkNjA4YTA4MDlkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoicGFya3NoYXJrLWR1ZGV3aGVyZWlzbXljYXIiLCJzZXNzaW9uX3N0YXRlIjoiNjY3YmYzNWItMjE2ZS00NjFjLTgyNDctZDlmY2RlYWZlZjk1IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIvKiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1qYXZhLW9jdC0yMDIxIiwib2ZmbGluZV9hY2Nlc3MiLCJ1bWFfYXV0aG9yaXphdGlvbiJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InBhcmtzaGFyay1kdWRld2hlcmVpc215Y2FyIjp7InJvbGVzIjpbIm1hbmFnZXIiLCJtZW1iZXIiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfSwib3JkZXIiOnsicm9sZXMiOlsidW1hX3Byb3RlY3Rpb24iLCJhZG1pbiIsImN1c3RvbWVyIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwic2lkIjoiNjY3YmYzNWItMjE2ZS00NjFjLTgyNDctZDlmY2RlYWZlZjk1IiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJuYW1lIjoiUnViZW4gTmV2ZW4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJydWJlbiIsImdpdmVuX25hbWUiOiJSdWJlbiIsImZhbWlseV9uYW1lIjoiTmV2ZW4ifQ.l0dD0S-G-irQARcZdIIUxZBM9W_EMOb_Q9ItwAgdEQB2V5Hb13LJsjXE8w9htGdtwysT6DGlO8rt57EuBFGQYqmIYB1T2JGjAovQUSP4CdgNvGtXD6vuMvUwbynqwlAKmTxt29702VcjUnq9DlnuMbGj91vzbfkPbJvNCksMzKC60PSkXgkSw9D7Fmo-zbkBrJaUZx9JPFehK0sjNAYpQRUTPMrFwHE7SYcj1_6XXvzbfpkc2sXk9CU1qXGDaLUPhDykIAEBGy-zqbg14umdFcm8fO88L1SCCJyQfzrN_qZPWdCkwUWv_Y0fhFud8AiYQ86YO0bskoyTGoCjnvuEpQ";
    }

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
                .header("Authorization", "Bearer " + token)
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
    void getAllDivisions() {
        List<DivisionDto> divisions = RestAssured
                .given()
                .header("Authorization", "Bearer " + token)
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
}