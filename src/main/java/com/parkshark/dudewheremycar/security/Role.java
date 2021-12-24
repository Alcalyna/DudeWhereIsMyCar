package com.parkshark.dudewheremycar.security;

import com.google.common.collect.Lists;
import java.util.List;

import static com.parkshark.dudewheremycar.security.Feature.*;

public enum Role {
    MANAGER("admin", GET_ADMIN_PARKSHARK, GET_CUSTOMER_PARKSHARK,
            CREATE_PARKING_LOT_PARKSHARK, GET_ALL_PARKING_LOTS_PARKSHARK,
            GET_ALL_PARKING_SPOT_ALLOCATIONS_PARKSHARK, GET_ALL_DIVISIONS_PARKSHARK,
            CREATE_DIVISION_PARKSHARK, GET_DIVISION_BY_ID_PARKSHARK, CREATE_SUBDIVISION_PARKSHARK),
    MEMBER("member", GET_CUSTOMER_PARKSHARK, CREATE_PARKING_SPOT_ALLOCATION_PARKSHARK,
            STOP_PARKING_SPOT_ALLOCATION_PARKSHARK,
            CREATE_MEMBER_PARKSHARK);

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
