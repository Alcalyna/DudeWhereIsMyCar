package com.parkshark.dudewheremycar.service.allocations;

import com.parkshark.dudewheremycar.domain.parkingspotallocations.ParkingSpotAllocation;
import com.parkshark.dudewheremycar.service.members.MemberMapper;
import com.parkshark.dudewheremycar.service.parkinglots.ParkingLotMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotAllocationMapper {

    private final MemberMapper memberMapper;
    private final ParkingLotMapper parkingLotMapper;

    @Autowired
    public ParkingSpotAllocationMapper(MemberMapper memberMapper, ParkingLotMapper parkingLotMapper) {
        this.memberMapper = memberMapper;
        this.parkingLotMapper = parkingLotMapper;
    }

    public ParkingSpotAllocation mapParkingSpotAllocationDtoToParkingSpotAllocation(
            ParkingSpotAllocationDto parkingSpotAllocationDtoToMap) {
        ParkingSpotAllocation mappedParkingSpotAllocation = ParkingSpotAllocation.ParkingSpotAllocationBuilder
                .aParkingSpotAllocation()
                .withMember(memberMapper.mapDtoToMember(parkingSpotAllocationDtoToMap.getMemberDto()))
                .withParkingLot(parkingLotMapper.mapDtoToParkingLot(parkingSpotAllocationDtoToMap.getParkingLotDto()))
                .withLicensePlate(parkingSpotAllocationDtoToMap.getLicensePlate())
                .build();
        return mappedParkingSpotAllocation;
    }

    public ParkingSpotAllocationDto mapParkingSpotAllocationToParkingSpotAllocationDto(
            ParkingSpotAllocation createdParkingSpotAllocationToMap) {
        ParkingSpotAllocationDto mappedParkingSpotAllocationDto = ParkingSpotAllocationDto.ParkingSpotAllocationDtoBuilder
                .aParkingSpotAllocationDto()
                .withId(createdParkingSpotAllocationToMap.getId())
                .withMemberDto(memberMapper.mapMemberToDto(createdParkingSpotAllocationToMap.getMember()))
                .withParkingLotDto(parkingLotMapper.mapParkingLotToDto(createdParkingSpotAllocationToMap.getParkingLot()))
                .withLicensePlate(createdParkingSpotAllocationToMap.getLicensePlate())
                .withStartTime(createdParkingSpotAllocationToMap.getStartTime())
                .withStopTime(createdParkingSpotAllocationToMap.getStopTime())
                .build();
        return mappedParkingSpotAllocationDto;
    }
}
