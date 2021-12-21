package com.parkshark.dudewheremycar.domain.information;

import java.util.UUID;

public class EmailAddress {

    private final UUID id;
    private final String username;
    private final String domain;

    public EmailAddress(String username, String domain) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.domain = domain;
    }
}
