package com.parkshark.dudewheremycar.domain.exceptions;

public class InvalidEmailAddressInformationException extends RuntimeException {
    public InvalidEmailAddressInformationException(String message) {
        super(message);
    }
}
