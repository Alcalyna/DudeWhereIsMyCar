package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidContactPersonInformationException;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name= "contact_persons")
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
        validateContactPersonInformation(emailAddress,phoneNumber,mobileNumber);
        this.id = UUID.randomUUID();
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.mobileNumber = mobileNumber;
    }

    private void validateContactPersonInformation(EmailAddress emailAddress, String phoneNumber, String mobileNumber) {
        if(emailAddress == null){
            throw new InvalidContactPersonInformationException("A contact person requires a zip code");
        }
        if(phoneNumber == null && mobileNumber == null){
            throw new InvalidContactPersonInformationException("A contact person requires at least a phone number or a mobile number");
        }
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
