package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.CreateSubdivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionSubdivisionDto;
import com.parkshark.dudewheremycar.service.divisions.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path="divisions")
public class DivisionController {

    private final Logger logger = LoggerFactory.getLogger(DivisionController.class);

    private final DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('CREATE_DIVISION_PARKSHARK')")
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        logger.info("Creating division started!");
        DivisionDto result = divisionService.addDivision(createDivisionDto);
        logger.info("Creating division finished!");
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('GET_ALL_DIVISIONS_PARKSHARK')")
    public List<DivisionDto> getAllDivisions() {
        logger.info("Getting all divisions started!");
        List<DivisionDto> result = divisionService.getDivisions();
        logger.info("Getting all divisions finished!");
        return result;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('CREATE_SUBDIVISION_PARKSHARK')")
    public DivisionSubdivisionDto createSubdivision(@PathVariable("id") UUID id, @RequestBody CreateSubdivisionDto createSubdivisionDto) {
        logger.info("Creating a subdivision started!");
        DivisionSubdivisionDto divisionSubdivisionDto = divisionService.addSubdivision(id, createSubdivisionDto);
        logger.info("Creating a subdivision finished!");
        return divisionSubdivisionDto;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('GET_DIVISION_BY_ID_PARKSHARK')")
    public DivisionDto getDivisionById(@PathVariable("id") UUID id) {
        logger.info("Getting a division by id started!");
        DivisionDto divisionDto = divisionService.getById(id);
        logger.info("Getting a division by id finished!");
        return divisionDto;
    }
}
