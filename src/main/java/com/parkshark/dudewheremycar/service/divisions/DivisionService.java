
package com.parkshark.dudewheremycar.service.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.api.mappers.divisions.DivisionMapper;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.repository.divisions.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepository;

    private DivisionMapper divisionMapper;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
    }

    public DivisionDto addDivision(CreateDivisionDto createDivisionDto) {
        Division division = divisionMapper.mapCreateDivisionDtoToDivision(createDivisionDto);
        divisionRepository.save(division);
        return divisionMapper.mapDivisionToDivisionDto(division);
    }

    public List<DivisionDto> getDivisions() {
        return divisionRepository.findAll().stream().map(division -> divisionMapper.mapDivisionToDivisionDto(division)).collect(Collectors.toList());
    }
}
