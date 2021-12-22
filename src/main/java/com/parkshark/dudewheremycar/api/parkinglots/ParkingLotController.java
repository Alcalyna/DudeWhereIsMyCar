
package com.parkshark.dudewheremycar.api.parkinglots;

import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;
    private final ParkingLotMapper parkingLotMapper;
    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
        this.parkingLotMapper = new ParkingLotMapper();
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingLotDto createParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
        logger.info("Creating a parking lot started");
        ParkingLot mappedParkingLot = parkingLotMapper.mapDtoToParkingLot(parkingLotDto);
        ParkingLot createdParkingLot = parkingLotService.createParkingLot(mappedParkingLot);
        ParkingLotDto createdParkingLotDto = parkingLotMapper.mapParkingLotToDto(createdParkingLot);
        logger.info("Creating a parking lot finished");
        return createdParkingLotDto;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ParkingLotDto> getAllParkingLots() {
        logger.info("Getting all parking lots started");
        List<ParkingLot> allParkingLots = parkingLotService.getAllParkingLots();
        List<ParkingLotDto> allParkingLotDtos = allParkingLots.stream().map(parkingLotMapper::mapParkingLotToDto).toList();
        logger.info("Getting all parking lots finished");
        return allParkingLotDtos;
    }
}
