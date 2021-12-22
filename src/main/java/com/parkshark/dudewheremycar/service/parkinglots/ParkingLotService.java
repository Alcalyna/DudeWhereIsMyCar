
package com.parkshark.dudewheremycar.service.parkinglots;

import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.repository.parkinglots.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
    }

    public ParkingLot createParkingLot(ParkingLot parkingLot) {
        return parkingLotRepository.save(parkingLot);
    }
}
