package com.parkshark.dudewheremycar.api.members;

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
                .withMemberId(memberToMap.getMemberId())
                .build();
        return createdMemberDto;
    }

}
