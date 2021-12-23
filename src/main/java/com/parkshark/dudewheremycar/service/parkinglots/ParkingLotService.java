
package com.parkshark.dudewheremycar.service.parkinglots;

import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.repository.parkinglots.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final ParkingLotMapper parkingLotMapper;

    @Autowired
    public ParkingLotService(ParkingLotRepository parkingLotRepository, ParkingLotMapper parkingLotMapper) {
        this.parkingLotRepository = parkingLotRepository;
        this.parkingLotMapper = parkingLotMapper;
    }

    public ParkingLotDto createParkingLot(ParkingLotDto parkingLotDto) {
        ParkingLot mappedParkingLot = parkingLotMapper.mapDtoToParkingLot(parkingLotDto);
        ParkingLot savedParkingLot = parkingLotRepository.save(mappedParkingLot);
        ParkingLotDto mappedParkingLotDto = parkingLotMapper.mapParkingLotToDto(savedParkingLot);
        return mappedParkingLotDto;
    }

    public List<ParkingLotSummaryDto> getAllParkingLots() {
        List<ParkingLot> allParkingLots = parkingLotRepository.findAll();
        List<ParkingLotSummaryDto> allParkingLotSummaryDtos =
                allParkingLots
                .stream()
                .map(parkingLotMapper::mapParkingLotToSummaryDto)
                .toList();
        return allParkingLotSummaryDtos;
    }
}
