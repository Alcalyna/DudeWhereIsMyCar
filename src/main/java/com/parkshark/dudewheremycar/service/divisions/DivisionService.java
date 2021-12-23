
package com.parkshark.dudewheremycar.service.divisions;

import com.parkshark.dudewheremycar.api.dtos.divisions.CreateDivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.CreateSubdivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionDto;
import com.parkshark.dudewheremycar.api.dtos.divisions.DivisionSubdivisionDto;
import com.parkshark.dudewheremycar.api.mappers.divisions.DivisionMapper;
import com.parkshark.dudewheremycar.domain.divisions.Division;
import com.parkshark.dudewheremycar.domain.divisions.DivisionSubdivision;
import com.parkshark.dudewheremycar.repository.divisions.DivisionRepository;
import com.parkshark.dudewheremycar.repository.divisions.DivisionSubdivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DivisionService {
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String RESET = "\u001B[0m";

    @Autowired
    private final DivisionRepository divisionRepository;
    private final DivisionMapper divisionMapper;
    private final DivisionSubdivisionRepository divisionSubdivisionRepository;

    public DivisionService(DivisionRepository divisionRepository, DivisionMapper divisionMapper, DivisionSubdivisionRepository divisionSubdivisionRepository) {
        this.divisionRepository = divisionRepository;
        this.divisionMapper = divisionMapper;
        this.divisionSubdivisionRepository = divisionSubdivisionRepository;
    }

    public DivisionDto addDivision(CreateDivisionDto createDivisionDto) {
        Division division = divisionMapper.mapCreateDivisionDtoToDivision(createDivisionDto);
        divisionRepository.save(division);
        DivisionDto divisionDto = divisionMapper.mapDivisionToDivisionDto(division);
        divisionDto.setDivisions(getAllSubdivisionById(division.getId()));
        return divisionDto;
    }

    public List<DivisionDto> getDivisions() {
        return divisionRepository.findAll().stream()
                .map(division -> divisionMapper.mapDivisionToDivisionDto(division).setDivisions(getAllSubdivisionById(division.getId())))
                .collect(Collectors.toList());
    }

    public List<DivisionDto> getAllSubdivisionById(UUID parentId) {
       return divisionSubdivisionRepository.findByIdParent(parentId).stream()
               .map(divisionSubdivision -> divisionSubdivision.getIdSubdivision())
               .map(idDivision -> divisionRepository.getById(idDivision))
               .map(division -> divisionMapper.mapDivisionToDivisionDto(division))
               .collect(Collectors.toList());
    }

    public DivisionSubdivisionDto addSubdivision(UUID divisionId, CreateSubdivisionDto createSubdivisionDto) {
        Division parent = divisionRepository.findById(divisionId).orElse(null);
//        if(parent == null) {
//            throw new NullPointerException("This division doesn't exist!");
//        }
        Division subdivision = divisionRepository.findById(createSubdivisionDto.getSubdivisionId()).orElse(null);
//        if(subdivision == null) {
//            throw new NullPointerException("This subdivision doesn't exist! Please create it before.");
//        }
        DivisionSubdivision divisionSubdivision = new DivisionSubdivision(parent.getId(), subdivision.getId());
//        if(divisionSubdivisionRepository.existsById(subdivision.getId())) {
//            throw new IllegalArgumentException("This subdivision already has a parent!");
//        }
        divisionSubdivisionRepository.save(divisionSubdivision);
        DivisionSubdivisionDto divisionSubdivisionDto = new DivisionSubdivisionDto(divisionSubdivision.getIdParent(), divisionSubdivision.getIdSubdivision());
        return divisionSubdivisionDto;
    }
}
