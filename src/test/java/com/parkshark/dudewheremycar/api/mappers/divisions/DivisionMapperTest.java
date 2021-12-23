package com.parkshark.dudewheremycar.api.mappers.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
class DivisionMapperTest {

    @Autowired
    private DivisionMapper divisionMapper;

    @Test
    void givenAWrongId() {
        Throwable exception = catchThrowable(() -> divisionMapper.mapCreateDivisionDtoToDivision(new CreateDivisionDto()
                .setName("Garden Dolphin")
                .setOriginalName("Park Shark")
                .setDirectorId(UUID.fromString("66241ef1-c725-4e0a-afcc-026578e40afc"))));

        Assertions.assertEquals(exception.getClass(), NullPointerException.class);
        Assertions.assertEquals("This director doesn't exist!",exception.getMessage());
    }
}