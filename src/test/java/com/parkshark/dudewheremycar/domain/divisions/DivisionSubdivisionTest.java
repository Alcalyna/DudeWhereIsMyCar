package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidDivisionSubdivisionInformationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class DivisionSubdivisionTest {

    @Test
    void createSubdivisionWithSameIdAsParent() {
        Throwable exception = catchThrowable(() -> new DivisionSubdivision(UUID.fromString("e0e25604-2c5e-4688-b3eb-3c8fc45980c1"), UUID.fromString("e0e25604-2c5e-4688-b3eb-3c8fc45980c1")));

        Assertions.assertEquals(InvalidDivisionSubdivisionInformationException.class, exception.getClass());
        Assertions.assertEquals("A division can't be its own subdivision!",exception.getMessage());
    }
}