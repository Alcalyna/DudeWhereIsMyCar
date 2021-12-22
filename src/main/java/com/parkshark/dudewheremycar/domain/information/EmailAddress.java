package com.parkshark.dudewheremycar.domain.information;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parkshark.dudewheremycar.domain.exceptions.InvalidEmailAddressInformationException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name= "email_addresses")
public class EmailAddress {

    @Id
    private UUID id;

    @Column(name= "username")
    private String username;

    @Column(name= "domain")
    private String domain;

    public EmailAddress(String username, String domain) {
        validateEmailAddressInformation(username,domain);
        this.id = UUID.randomUUID();
        this.username = username;
        this.domain = domain;
    }

    private void validateEmailAddressInformation(String username, String domain) {
        if(username == null){
            throw new InvalidEmailAddressInformationException("An email requires a username");
        }
        if(username.contains("@")){
            throw new InvalidEmailAddressInformationException("An emails username cannot contain '@'");
        }
        if(domain == null){
            throw new InvalidEmailAddressInformationException("An email requires a domain");
        }
        if(domain.contains("@")){
            throw new InvalidEmailAddressInformationException("An emails domain cannot contain '@'");
        }
    }

    private EmailAddress() {
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDomain() {
        return domain;
    }

}
