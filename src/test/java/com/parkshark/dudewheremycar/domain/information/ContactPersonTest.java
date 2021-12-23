package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidContactPersonInformationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactPersonTest {

    @Test
    void givenAContactPersonWithNoEmailAddress_whenCreatingContactPerson_InvalidContactPersonInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidContactPersonInformationException.class)
                .isThrownBy(() ->
                        new ContactPerson(null,
                                "0123456789", "9876543210"));
    }

    @Test
    void givenAContactPersonWithNoNumbers_whenCreatingContactPerson_InvalidContactPersonInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidContactPersonInformationException.class)
                .isThrownBy(() ->
                        new ContactPerson(new EmailAddress("myUsername","switch.com"),
                                null, null));
    }



}