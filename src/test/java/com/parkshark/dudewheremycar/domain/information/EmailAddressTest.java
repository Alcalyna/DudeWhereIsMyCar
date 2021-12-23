package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.exceptions.information.InvalidEmailAddressInformationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EmailAddressTest {

    @Test
    void givenAnEmailAddressWithNoUsername_whenCreatingEmailAddress_InvalidEmailAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidEmailAddressInformationException.class)
                .isThrownBy(() ->
                        new EmailAddress(null,"switch.com"));
    }

    @Test
    void givenAnEmailAddressWithAtInUsername_whenCreatingEmailAddress_InvalidEmailAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidEmailAddressInformationException.class)
                .isThrownBy(() ->
                        new EmailAddress("myUser@name","switch.com"));
    }

    @Test
    void givenAnEmailAddressWithNoDomain_whenCreatingEmailAddress_InvalidEmailAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidEmailAddressInformationException.class)
                .isThrownBy(() ->
                        new EmailAddress("myUsername",null));
    }

    @Test
    void givenAnEmailAddressWithAtInDomain_whenCreatingEmailAddress_InvalidEmailAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidEmailAddressInformationException.class)
                .isThrownBy(() ->
                        new EmailAddress("myUsername","switc@h.com"));
    }

}