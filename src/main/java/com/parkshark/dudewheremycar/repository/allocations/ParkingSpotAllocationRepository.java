package com.parkshark.dudewheremycar.repository.allocations;

import com.parkshark.dudewheremycar.domain.parkingspotallocations.ParkingSpotAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingSpotAllocationRepository extends JpaRepository<ParkingSpotAllocation, UUID> {
}
