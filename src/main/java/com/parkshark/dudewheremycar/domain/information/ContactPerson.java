package com.parkshark.dudewheremycar.domain.information;

import java.util.UUID;

public class ContactPerson {

    private final UUID id;
    private final EmailAddress emailAddress;
    private final String phoneNumber;
    private final String mobileNumber;


    public ContactPerson(EmailAddress emailAddress, String phoneNumber, String mobileNumber) {
        this.id = UUID.randomUUID();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }
}
