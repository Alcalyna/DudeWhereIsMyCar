package com.parkshark.dudewheremycar.security;

import com.google.common.collect.Lists;
import java.util.List;

import static com.parkshark.dudewheremycar.security.Feature.*;

public enum Role {
    MANAGER("admin", GET_ADMIN_PARKSHARK, GET_CUSTOMER_PARKSHARK),
    MEMBER("member", GET_CUSTOMER_PARKSHARK);

    private final String label;
    private final List<Feature> featureList;

    Role(String label, Feature... featureList) {
        this.label = label;
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatures() {
        return featureList;
    }

    public String getLabel() {
        return label;
    }
}
