package com.parkshark.dudewheremycar.domain.information;

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
        this.id = UUID.randomUUID();
        this.username = username;
        this.domain = domain;
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
