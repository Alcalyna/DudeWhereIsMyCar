package com.parkshark.dudewheremycar.service.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.api.mappers.divisions.DivisionMapper;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.repository.divisions.DivisionRepository;
import org.springframework.stereotype.Service;

@Service
public class DivisionService {

    private DivisionRepository divisionRepository;
    private DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto addDivision(CreateDivisionDto createDivisionDto) {
        Division division = divisionMapper.mapCreateDivisionDtoToDivision(createDivisionDto);
        divisionRepository.addDivision(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }
}
