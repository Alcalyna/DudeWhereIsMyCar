package com.parkshark.dudewheremycar.domain.exceptions;

public class InvalidCityInformationException extends RuntimeException {

    public InvalidCityInformationException(String message) {
        super(message);
    }
}