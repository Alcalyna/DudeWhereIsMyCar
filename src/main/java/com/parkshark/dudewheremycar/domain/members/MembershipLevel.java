package com.parkshark.dudewheremycar.domain.members;

public enum MembershipLevel {
    BRONZE(0, 0, 4),
    SILVER(10,0.2f,4),
    GOLD(40,0.3f,24);

    private float monthlyCostInEur;
    private float allocationReduction;
    private int allowedAllocationTimeInHours;

    MembershipLevel(float monthlyCost, float allocationReduction, int allowedAllocationTimeInHours) {
        this.monthlyCostInEur = monthlyCost;
        this.allocationReduction = allocationReduction;
        this.allowedAllocationTimeInHours = allowedAllocationTimeInHours;
    }

    public float getMonthlyCost() {
        return monthlyCostInEur;
    }

    public float getAllocationReduction() {
        return allocationReduction;
    }

    public int getAllowedAllocationTimeInHours() {
        return allowedAllocationTimeInHours;
    }
}
