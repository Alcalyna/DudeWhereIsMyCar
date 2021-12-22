package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidAddressInformationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class AddressTest {

    @Test
    void givenAnAddressWithNoStreetName_whenCreatingAddress_InvalidAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidAddressInformationException.class)
                .isThrownBy(() ->
                      new Address(null, "69", new City("3000", "myCity")));
    }

    @Test
    void givenAnAddressWithNoStreetNumber_whenCreatingAddress_InvalidAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidAddressInformationException.class)
                .isThrownBy(() ->
                        new Address("streetName", null, new City("3000", "myCity")));
    }

    @Test
    void givenAnAddressWithNoCity_whenCreatingAddress_InvalidAddressInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidAddressInformationException.class)
                .isThrownBy(() ->
                        new Address("streetName", "69", null));
    }
}