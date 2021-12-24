package com.parkshark.dudewheremycar.service.members;

import com.parkshark.dudewheremycar.domain.members.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member mapDtoToMember(MemberDto memberDtoToMap){
        Member createdMember = Member.MemberBuilder.aMember()
                .withFirstName(memberDtoToMap.getFirstName())
                .withLastName(memberDtoToMap.getLastName())
                .withAddress(memberDtoToMap.getAddress())
                .withPhoneNumber(memberDtoToMap.getPhoneNumber())
                .withMobileNumber(memberDtoToMap.getMobileNumber())
                .withEmailAddress(memberDtoToMap.getEmailAddress())
                .withLicensePlate(memberDtoToMap.getLicensePlate())
                .withMembershipLevel(memberDtoToMap.getMembershipLevel())
                .build();
        return createdMember;
    }

    public MemberDto mapMemberToDto(Member memberToMap){
        MemberDto createdMemberDto = MemberDto.MemberDtoBuilder.aMemberDtoBuilder()
                .withFirstName(memberToMap.getFirstName())
                .withLastName(memberToMap.getLastName())
                .withAddress(memberToMap.getAddress())
                .withPhoneNumber(memberToMap.getPhoneNumber())
                .withMobileNumber(memberToMap.getMobileNumber())
                .withEmailAddress(memberToMap.getEmailAddress())
                .withRegistrationDate(memberToMap.getRegistrationDate())
                .withLicensePlate(memberToMap.getLicensePlate())
                .withMemberId(memberToMap.getMemberId())
                .withMembershipLevel(memberToMap.getMembershipLevel())
                .build();
        return createdMemberDto;
    }

    public MemberSummaryDto mapMemberToSummaryDto(Member memberToMap){
        MemberSummaryDto createdMemberSummaryDto = MemberSummaryDto.MemberSummaryDtoBuilder.aMemberSummaryDtoBuilder()
                .withFirstName(memberToMap.getFirstName())
                .withLastName(memberToMap.getLastName())
                .withPhoneNumber(memberToMap.getPhoneNumber())
                .withEmailAddress(memberToMap.getEmailAddress())
                .withRegistrationDate(memberToMap.getRegistrationDate())
                .withLicensePlateNumber(memberToMap.getLicensePlate().getLicensePlateNumber())
                .withMemberId(memberToMap.getMemberId())
                .build();
        return createdMemberSummaryDto;
    }

}
