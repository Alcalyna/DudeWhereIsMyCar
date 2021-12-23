package com.parkshark.dudewheremycar.domain.information;

import com.parkshark.dudewheremycar.domain.exceptions.InvalidCityInformationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class CityTest {

    @Test
    void givenACityWithNoZipCode_whenCreatingCity_InvalidCityInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidCityInformationException.class)
                .isThrownBy(() ->
                        new City(null,"myCity"));
    }

    @Test
    void givenACityWithNoName_whenCreatingCity_InvalidCityInformationExceptionIsThrown(){
        Assertions.assertThatExceptionOfType(InvalidCityInformationException.class)
                .isThrownBy(() ->
                        new City("3000",null));
    }

}