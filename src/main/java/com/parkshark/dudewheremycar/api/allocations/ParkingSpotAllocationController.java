package com.parkshark.dudewheremycar.api.allocations;

import com.parkshark.dudewheremycar.service.allocations.ParkingSpotAllocationDto;
import com.parkshark.dudewheremycar.service.allocations.ParkingSpotAllocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/allocations")
public class ParkingSpotAllocationController {

    private final ParkingSpotAllocationService parkingSpotAllocationService;
    private final Logger logger = LoggerFactory.getLogger(ParkingSpotAllocationController.class);

    @Autowired
    public ParkingSpotAllocationController(ParkingSpotAllocationService parkingSpotAllocationService) {
        this.parkingSpotAllocationService = parkingSpotAllocationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('CREATE_PARKING_SPOT_ALLOCATION_PARKSHARK')")
    public ParkingSpotAllocationDto createParkingSpotAllocation(@RequestBody ParkingSpotAllocationDto parkingSpotAllocationDto) {
        logger.info("Creating parking spot allocation started");
        ParkingSpotAllocationDto createdParkingSpotAllocationDto =
                parkingSpotAllocationService.createParkingSpotAllocation(parkingSpotAllocationDto);
        logger.info("Creating parking spot allocation finished");
        return createdParkingSpotAllocationDto;
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('STOP_PARKING_SPOT_ALLOCATION_PARKSHARK')")
    public ParkingSpotAllocationDto stopParkingSpotAllocation(@PathVariable UUID id) {
        logger.info("Stopping parking spot allocation started");
        ParkingSpotAllocationDto stoppedParkingSpotAllocationDto =
                parkingSpotAllocationService.stopParkingSpotAllocation(id);
        logger.info("Stopping parking spot allocation finished");
        return stoppedParkingSpotAllocationDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('GET_ALL_PARKING_SPOT_ALLOCATIONS_PARKSHARK')")
    public List<ParkingSpotAllocationDto> getAllParkingSpotAllocations(@RequestParam(required = false) Boolean active,
                                                                       @RequestParam(required = false) int limit,
                                                                       @RequestParam(required = false) SortOrder order) {
        logger.info("Getting all parking spot allocations started");
        List<ParkingSpotAllocationDto> matchingParkingSpotAllocationDtos =
                parkingSpotAllocationService.getAllParkingSpotAllocations(active, limit, order);
        logger.info("Getting all parking spot allocations finished");
        return matchingParkingSpotAllocationDtos;
    }
}
