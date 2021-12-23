
package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.service.divisions.DivisionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @PreAuthorize("hasAuthority('CREATE_DIVISION')")
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        logger.info("Creating division started!");
        DivisionDto result = divisionService.addDivision(createDivisionDto);
        logger.info("Creating division finished!");
        return result;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('GET_DIVISIONS')")
    public List<DivisionDto> getAllDivisions() {
        logger.info("Getting all divisions started!");
        List<DivisionDto> result = divisionService.getDivisions();
        logger.info("Getting all divisions finished!");
        return result;
    }
}
