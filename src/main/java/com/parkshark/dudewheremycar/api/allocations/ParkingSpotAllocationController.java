package com.parkshark.dudewheremycar.api.allocations;

import com.parkshark.dudewheremycar.api.parkinglots.ParkingLotController;
import com.parkshark.dudewheremycar.domain.parkingspotallocations.ParkingSpotAllocation;
import com.parkshark.dudewheremycar.service.allocations.ParkingSpotAllocationDto;
import com.parkshark.dudewheremycar.service.allocations.ParkingSpotAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocations")
public class ParkingSpotAllocationController {

    private final ParkingSpotAllocationService parkingSpotAllocationService;
    private final Logger logger = LoggerFactory.getLogger(ParkingSpotAllocationController.class);

    @Autowired
    public ParkingSpotAllocationController(ParkingSpotAllocationService parkingSpotAllocationService) {
        this.parkingSpotAllocationService = parkingSpotAllocationService;
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ParkingSpotAllocationDto createParkingSpotAllocation(@RequestBody ParkingSpotAllocationDto parkingSpotAllocationDto){
        logger.info("Creating parking spot allocation started");
        ParkingSpotAllocationDto createdParkingSpotAllocationDto =
                parkingSpotAllocationService.createParkingSpotAllocation(parkingSpotAllocationDto);
        logger.info("Creating parking spot allocation finished");
        return createdParkingSpotAllocationDto;
    }
}
