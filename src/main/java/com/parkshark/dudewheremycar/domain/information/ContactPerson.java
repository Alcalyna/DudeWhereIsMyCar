package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "contact_persons")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContactPerson {

    @Id
    private UUID id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name= "email_address")
    private EmailAddress emailAddress;

    @Column(name= "phone_number")
    private String phoneNumber;

    @Column(name= "mobile_number")
    private String mobileNumber;

    public ContactPerson(EmailAddress emailAddress, String phoneNumber, String mobileNumber) {
        this.id = UUID.randomUUID();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }

    private ContactPerson() {
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
