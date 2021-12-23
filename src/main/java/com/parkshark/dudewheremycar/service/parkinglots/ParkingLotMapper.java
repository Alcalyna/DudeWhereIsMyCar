package com.parkshark.dudewheremycar.service.parkinglots;

import com.parkshark.dudewheremycar.api.mappers.divisions.DivisionMapper;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingLotMapper {

    private final DivisionMapper divisionMapper;

    @Autowired
    public ParkingLotMapper(DivisionMapper divisionMapper) {
        this.divisionMapper = divisionMapper;
    }

    public ParkingLot mapDtoToParkingLot(ParkingLotDto parkingLotDtoToMap) {
        ParkingLot createdParkingLot = ParkingLot.ParkingLotBuilder.aParkingLot()
                .withName(parkingLotDtoToMap.getName())
                .withParkingLotCategory(parkingLotDtoToMap.getParkingLotCategory())
                .withMaxCapacity(parkingLotDtoToMap.getMaxCapacity())
                .withContactPerson(parkingLotDtoToMap.getContactPerson())
                .withAddress(parkingLotDtoToMap.getAddress())
                .withDivision(divisionMapper.mapDivisionDtoToDivision(parkingLotDtoToMap.getDivisionDto()))
                .withPricePerHour(parkingLotDtoToMap.getPricePerHour())
                .build();
        if(parkingLotDtoToMap.getId() != null){
            createdParkingLot.setId(parkingLotDtoToMap.getId());
        }
        return createdParkingLot;
    }

    public ParkingLotDto mapParkingLotToDto(ParkingLot parkingLotToMap) {
        ParkingLotDto createdParkingLotDto = ParkingLotDto.ParkingLotDtoBuilder.aParkingLotDto()
                .withId(parkingLotToMap.getId())
                .withName(parkingLotToMap.getName())
                .withParkingLotCategory(parkingLotToMap.getParkingLotCategory())
                .withMaxCapacity(parkingLotToMap.getMaxCapacity())
                .withContactPerson(parkingLotToMap.getContactPerson())
                .withAddress(parkingLotToMap.getAddress())
                .withDivisionDto(divisionMapper.mapDivisionToDivisionDto(parkingLotToMap.getDivision()))
                .withPricePerHour(parkingLotToMap.getPricePerHour())
                .build();
        return createdParkingLotDto;
    }

    public ParkingLotSummaryDto mapParkingLotToSummaryDto(ParkingLot parkingLotToMap) {
        ParkingLotSummaryDto createdParkingLotSummaryDto = ParkingLotSummaryDto.ParkingLotSummaryDtoBuilder.aParkingLotSummaryDto()
                .withId(parkingLotToMap.getId())
                .withName(parkingLotToMap.getName())
                .withMaxCapacity(parkingLotToMap.getMaxCapacity())
                .withContactPersonEmail(parkingLotToMap.getContactPerson().getEmailAddress())
                .withContactPersonPhone(parkingLotToMap.getContactPerson().getPhoneNumber())
                .withContactPersonMobile(parkingLotToMap.getContactPerson().getMobileNumber())
                .build();
        return createdParkingLotSummaryDto;
    }
}
