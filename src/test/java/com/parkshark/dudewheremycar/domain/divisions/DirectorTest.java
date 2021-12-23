package com.parkshark.dudewheremycar.domain.divisions;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidDirectorInformationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class DirectorTest {
    @Test
    void givenCorrectInformation_WhenCallingTheConstructor_CreateADirector() {
        String firstName = "Calinh";
        String lastName = "Catceo";

        Director director = new Director(firstName, lastName);

        Assertions.assertTrue(!director.getId().toString().isBlank());
        Assertions.assertEquals("Calinh", director.getFirstName());
        Assertions.assertEquals("Catceo", director.getLastName());
    }

    @Test
    void givenAWrongFirstName_ThrowsError() {
        Throwable exception = catchThrowable(() -> new Director("", "Doe"));

        Assertions.assertEquals(exception.getClass(), InvalidDirectorInformationException.class);
        Assertions.assertEquals("Director requires a first name!",exception.getMessage());
    }

    @Test
    void givenAWrongLastName_ThrowsError() {
        Throwable exception = catchThrowable(() -> new Director("John", "   "));

        Assertions.assertEquals(exception.getClass(), InvalidDirectorInformationException.class);
        Assertions.assertEquals("Director requires a last name!",exception.getMessage());
    }
}