package com.parkshark.dudewheremycar.security;

import java.util.List;

import static com.parkshark.dudewheremycar.security.Feature.*;

public enum Role {
    MANAGER(List.of(GET_ADMIN_PARKSHARK, GET_CUSTOMER_PARKSHARK, CREATE_DIVISION, GET_DIVISIONS)),
    MEMBER(List.of(GET_CUSTOMER_PARKSHARK));

    private final List<Feature> featureList;

    Role(List<Feature> features) {
        this.featureList = features;
    }

    public List<Feature> getFeatures() {
        return featureList;
    }
}
