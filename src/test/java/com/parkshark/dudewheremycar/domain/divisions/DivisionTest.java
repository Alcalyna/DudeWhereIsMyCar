package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionInformationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class DivisionTest {
    Director director;

    @BeforeEach
    void init() {
        director = new Director("Calinh", "Corp");
    }

    @Test
    void givenAWrongName_ThrowsError() {
        Throwable exception = catchThrowable(() -> new Division("     ","Original name", director));

        Assertions.assertEquals(exception.getClass(), InvalidDivisionInformationException.class);
        Assertions.assertEquals("Division requires a name!",exception.getMessage());
    }

    @Test
    void givenAWrongDirector_ThrowsError() {
        Throwable exception = catchThrowable(() -> new Division("New name","Original name", null));

        Assertions.assertEquals(exception.getClass(), InvalidDivisionInformationException.class);
        Assertions.assertEquals("Division requires a director!",exception.getMessage());
    }
}