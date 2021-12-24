package com.parkshark.dudewheremycar.service.allocations;

import com.parkshark.dudewheremycar.domain.exceptions.ParkingSpotAllocationException;
import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.domain.parkinglots.ParkingLot;
import com.parkshark.dudewheremycar.domain.parkingspotallocations.ParkingSpotAllocation;
import com.parkshark.dudewheremycar.repository.allocations.ParkingSpotAllocationRepository;
import com.parkshark.dudewheremycar.repository.members.MemberRepository;
import com.parkshark.dudewheremycar.repository.parkinglots.ParkingLotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class ParkingSpotAllocationService {

    private final ParkingSpotAllocationRepository parkingSpotAllocationRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final MemberRepository memberRepository;
    private final ParkingSpotAllocationMapper parkingSpotAllocationMapper;

    @Autowired
    public ParkingSpotAllocationService(ParkingSpotAllocationRepository parkingSpotAllocationRepository, ParkingLotRepository parkingLotRepository, MemberRepository memberRepository, ParkingSpotAllocationMapper parkingSpotAllocationMapper) {
        this.parkingSpotAllocationRepository = parkingSpotAllocationRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.memberRepository = memberRepository;
        this.parkingSpotAllocationMapper = parkingSpotAllocationMapper;
    }

    public ParkingSpotAllocationDto createParkingSpotAllocation(ParkingSpotAllocationDto parkingSpotAllocationDto) {
        ParkingSpotAllocation parkingSpotAllocation = parkingSpotAllocationMapper
                .mapParkingSpotAllocationDtoToParkingSpotAllocation(parkingSpotAllocationDto);
        //TODO validate that parkingspotallocation is allowed
        validateParkingLotHasASpot(parkingSpotAllocation);
        validateMemberAllowedToPark(parkingSpotAllocation);

        ParkingSpotAllocation createdParkingSpotAllocation = parkingSpotAllocationRepository.save(parkingSpotAllocation);
        ParkingSpotAllocationDto createdParkingSpotAllocationDto = parkingSpotAllocationMapper
                .mapParkingSpotAllocationToParkingSpotAllocationDto(createdParkingSpotAllocation);
        return createdParkingSpotAllocationDto;
    }

    private void validateParkingLotHasASpot(ParkingSpotAllocation parkingSpotAllocation) {
        ParkingLot parkingLot = parkingSpotAllocation.getParkingLot();
        validateParkingLotExists(parkingLot);
        List<ParkingSpotAllocation> activeParkingSpotAllocationsForSameParkingLot =
                getAllActiveParkingSpotAllocations().stream()
                        .filter(allocation -> allocation.getParkingLot().equals(parkingSpotAllocation.getParkingLot()))
                        .toList();
        if (activeParkingSpotAllocationsForSameParkingLot.size() >= parkingLot.getMaxCapacity()) {
            throw new ParkingSpotAllocationException("The parking lot with id: " + parkingLot.getId() + " is at max capacity");
        }
    }

    private void validateParkingLotExists(ParkingLot parkingLot) {
        if (parkingLotRepository.findById(parkingLot.getId()).isEmpty()) {
            throw new ParkingSpotAllocationException("Parking lot with id: " + parkingLot.getId() + " does not exist");
        }
    }

    private void validateMemberAllowedToPark(ParkingSpotAllocation parkingSpotAllocation) {
        Member member = parkingSpotAllocation.getMember();
        validateMemberExists(member);
        //Future gold member level exclusion on validation
        validateMemberHasNoCurrentAllocation(member);
        String licensePlateNumberOfMember = member.getLicensePlate().getLicensePlateNumber();
        String licensePlateNumberInAllocation = parkingSpotAllocation.getLicensePlate().getLicensePlateNumber();
        if (!licensePlateNumberOfMember.equals(licensePlateNumberInAllocation)) {
            throw new ParkingSpotAllocationException("License plate " + licensePlateNumberInAllocation +
                    " does not match member's license plate");
        }
    }

    private void validateMemberHasNoCurrentAllocation(Member member) {
        long currentActiveAllocationsForMember = getAllActiveParkingSpotAllocations().stream()
                .filter(parkingSpotAllocation -> parkingSpotAllocation.getMember().getMemberId().equals(member.getMemberId()))
                .count();
        if (currentActiveAllocationsForMember > 0) {
            throw new ParkingSpotAllocationException("The member with id: " + member.getMemberId()
                    + " already has currently active allocation");
        }
    }

    private void validateMemberExists(Member member) {
        if (memberRepository.findById(member.getMemberId()).isEmpty()) {
            throw new ParkingSpotAllocationException("Member with id: " + member.getMemberId() + " does not exist");
        }
    }

    public List<ParkingSpotAllocation> getAllActiveParkingSpotAllocations() {
        List<ParkingSpotAllocation> activeParkingSpotAllocations =
                getAllParkingSpotAllocations().stream()
                        .filter(parkingSpotAllocation -> parkingSpotAllocation.getStopTime() == null)
                        .toList();
        return activeParkingSpotAllocations;
    }

    private List<ParkingSpotAllocation> getAllInactiveParkingSpotAllocations() {
        List<ParkingSpotAllocation> activeParkingSpotAllocations =
                getAllParkingSpotAllocations().stream()
                        .filter(parkingSpotAllocation -> parkingSpotAllocation.getStopTime() != null)
                        .toList();
        return activeParkingSpotAllocations;
    }

    public List<ParkingSpotAllocation> getAllParkingSpotAllocations() {
        return parkingSpotAllocationRepository.findAll();
    }

    public List<ParkingSpotAllocationDto> getAllParkingSpotAllocations(Boolean active, int limit, SortOrder order) {
        List<ParkingSpotAllocation> allMatchingParkingSpotAllocations;
        if (active != null) {
            if (active) {
                allMatchingParkingSpotAllocations = getAllActiveParkingSpotAllocations();
            } else {
                allMatchingParkingSpotAllocations = getAllInactiveParkingSpotAllocations();
            }
        } else {
            allMatchingParkingSpotAllocations = getAllActiveParkingSpotAllocations();
        }
        if (limit != 0) {
            allMatchingParkingSpotAllocations = allMatchingParkingSpotAllocations.stream()
                    .limit(limit)
                    .toList();
        }
        if (order != null) {
            if (order.equals(SortOrder.DESCENDING)) {
                allMatchingParkingSpotAllocations = allMatchingParkingSpotAllocations.stream()
                        .sorted(Comparator.comparing(ParkingSpotAllocation::getStartTime,
                                Comparator.nullsLast(Comparator.reverseOrder())))
                        .toList();
            } else {
                allMatchingParkingSpotAllocations = allMatchingParkingSpotAllocations.stream()
                        .sorted(Comparator.comparing(ParkingSpotAllocation::getStartTime,
                                Comparator.nullsLast(Comparator.naturalOrder())))
                        .toList();
            }
        }
        List<ParkingSpotAllocationDto> allMatchingParkingSpotAllocationDtos =
                allMatchingParkingSpotAllocations.stream()
                        .map(parkingSpotAllocationMapper::mapParkingSpotAllocationToParkingSpotAllocationDto)
                        .toList();
        return allMatchingParkingSpotAllocationDtos;
    }

    @Transactional
    public ParkingSpotAllocationDto stopParkingSpotAllocation(UUID id) {
        ParkingSpotAllocation parkingSpotAllocationToStop = parkingSpotAllocationRepository
                .findById(id).orElse(null);
        if (parkingSpotAllocationToStop == null) {
            throw new ParkingSpotAllocationException("The parking spot allocation you are trying to stop does not exist");
        }
        parkingSpotAllocationToStop.stopAllocation();
        ParkingSpotAllocationDto stoppedParkingSpotAllocationDto = parkingSpotAllocationMapper
                .mapParkingSpotAllocationToParkingSpotAllocationDto(parkingSpotAllocationToStop);
        return stoppedParkingSpotAllocationDto;
    }
}
