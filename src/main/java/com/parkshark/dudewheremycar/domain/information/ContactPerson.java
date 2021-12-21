package com.parkshark.dudewheremycar.domain.information;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "contact_persons")
public class ContactPerson {

    @Id
    private final UUID id;

    @OneToOne
    @JoinColumn(name= "email_address")
    private final EmailAddress emailAddress;

    @Column(name= "phone_number")
    private final String phoneNumber;

    @Column(name= "mobile_number")
    private final String mobileNumber;

    public ContactPerson(EmailAddress emailAddress, String phoneNumber, String mobileNumber) {
        this.id = UUID.randomUUID();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }

    public UUID getId() {
        return id;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
