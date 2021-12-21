package com.parkshark.dudewheremycar.api.mappers.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.repository.divisions.DirectorRepository;
import org.springframework.stereotype.Component;

@Component
public class DivisionMapper {
    public static final String ANSI_GREEN = "\u001B[32m";
    private DirectorRepository directorRepository;

    public DivisionMapper(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public Division mapCreateDivisionDtoToDivision(CreateDivisionDto createDivisionDto) {
        System.out.println("The id is " + createDivisionDto.getDirectorId().toString());
        System.out.println(ANSI_GREEN + directorRepository.getDirectorById(createDivisionDto.getDirectorId()) + ANSI_GREEN);
        return new Division(createDivisionDto.getName(), createDivisionDto.getOriginalName(), directorRepository.getDirectorById(createDivisionDto.getDirectorId()));
    }

    public DivisionDto mapDivisionToDivisionDto(Division division) {
        return new DivisionDto(division.getId(), division.getName(), division.getOriginalName(), division.getDirector());
    }
}
