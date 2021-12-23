package com.parkshark.dudewheremycar.service.allocations;

import com.parkshark.dudewheremycar.domain.exceptions.ParkingSpotAllocationException;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.domain.parkingspotallocations.ParkingSpotAllocation;
import com.parkshark.dudewheremycar.repository.allocations.ParkingSpotAllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSpotAllocationService {

    private final ParkingSpotAllocationRepository parkingSpotAllocationRepository;
    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;

    @Autowired
    public ParkingSpotAllocationService(ParkingSpotAllocationRepository parkingSpotAllocationRepository, ParkingSpotAllocationMapper parkingSpotAllocationMapper) {
        this.parkingSpotAllocationRepository = parkingSpotAllocationRepository;
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
    }

    public ParkingSpotAllocationDto createParkingSpotAllocation(ParkingSpotAllocationDto parkingSpotAllocationDto){
        ParkingSpotAllocation parkingSpotAllocation = parkingSpotAllocationMapper
                .mapParkingSpotAllocationDtoToParkingSpotAllocation(parkingSpotAllocationDto);
        //TODO validate that parkingspotallocation is allowed

        validateParkingLotHasASpot(parkingSpotAllocation);

        ParkingSpotAllocation createdParkingSpotAllocation = parkingSpotAllocationRepository.save(parkingSpotAllocation);
        ParkingSpotAllocationDto createdParkingSpotAllocationDto = parkingSpotAllocationMapper
                .mapParkingSpotAllocationToParkingSpotAllocationDto(createdParkingSpotAllocation);
        return createdParkingSpotAllocationDto;
    }

    private void validateParkingLotHasASpot(ParkingSpotAllocation parkingSpotAllocation){
        ParkingLot parkingLot = parkingSpotAllocation.getParkingLot();
        List<ParkingSpotAllocation> activeParkingSpotAllocationsForSameParkingLot =
                getAllActiveParkingSpotAllocations().stream()
                        .filter(allocation -> allocation.getParkingLot().equals(parkingSpotAllocation.getParkingLot()))
                        .toList();
        if(activeParkingSpotAllocationsForSameParkingLot.size() >= parkingLot.getMaxCapacity()){
            throw new ParkingSpotAllocationException("The parking lot with id: " + parkingLot.getId() + " is at max capacity");
        }
    }

    public List<ParkingSpotAllocation> getAllActiveParkingSpotAllocations(){
       List<ParkingSpotAllocation> activeParkingSpotAllocations =
               getAllParkingSpotAllocations().stream()
               .filter(parkingSpotAllocation -> parkingSpotAllocation.getStopTime() == null)
               .toList();
       return activeParkingSpotAllocations;
    }

    public List<ParkingSpotAllocation> getAllParkingSpotAllocations(){
        return parkingSpotAllocationRepository.findAll();
    }
}
