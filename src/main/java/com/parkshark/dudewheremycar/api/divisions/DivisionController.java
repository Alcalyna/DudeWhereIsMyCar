package com.parkshark.dudewheremycar.api.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.service.divisions.DivisionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="divisions")
public class DivisionController {

    private DivisionService divisionService;

    public DivisionController(DivisionService divisionService) {
        this.divisionService = divisionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    //todo: as a Manager
    public DivisionDto createDivision(@RequestBody CreateDivisionDto createDivisionDto) {
        return divisionService.addDivision(createDivisionDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
//    @PreAuthorize("hasAuthority('GET_DIVISIONS')")
    public List<DivisionDto> getAllDivisions() {
        return divisionService.getDivisions();
    }
}
