package com.parkshark.dudewheremycar.api.mappers.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.repository.divisions.DirectorRepository;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {

    private DirectorRepository directorRepository;

    public DivisionMapper(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Division mapCreateDivisionDtoToDivision(CreateDivisionDto createDivisionDto) {
        return new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), directorRepository.getDirectorById(createDivisionDto.getDirectorId()));
    }

    public DivisionDto mapDivisionToDivisionDto(Division division) {
        return new DivisionDto(division.getId(), division.getName(), division.getOriginalName(), division.getDirector());
    }
}
