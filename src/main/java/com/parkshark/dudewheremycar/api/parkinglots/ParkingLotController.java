
package com.parkshark.dudewheremycar.api.parkinglots;

import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotDto;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotMapper;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotService;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotSummaryDto;
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
    private final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);

    @Autowired
    public ParkingLotController(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("CREATE_PARKING_LOT_PARKSHARK")
    public ParkingLotDto createParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
        logger.info("Creating a parking lot started");
        ParkingLotDto createdParkingLotDto = parkingLotService.createParkingLot(parkingLotDto);
        logger.info("Creating a parking lot finished");
        return createdParkingLotDto;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("GET_CUSTOMER_PARKSHARK")
    public List<ParkingLotSummaryDto> getAllParkingLots() {
        logger.info("Getting all parking lots started");
        List<ParkingLotSummaryDto> allParkingLotSummaryDtos = parkingLotService.getAllParkingLots();
        logger.info("Getting all parking lots finished");
        return allParkingLotSummaryDtos;
    }
}
