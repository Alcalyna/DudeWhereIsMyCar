package com.parkshark.dudewheremycar.repository.parkinglots;

import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, UUID> {


}
