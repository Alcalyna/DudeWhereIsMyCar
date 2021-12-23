
package com.parkshark.dudewheremycar.service.members;

import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.repository.members.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(MemberDto memberDto) {
        Member member = memberMapper.mapDtoToMember(memberDto);
        Member savedMember = memberRepository.save(member);
        MemberDto outputMemberDto = memberMapper.mapMemberToDto(savedMember);
        return outputMemberDto;
    }

    public List<MemberSummaryDto> getAllMembers(){
        List<Member> allMembers = memberRepository.findAll();
        List<MemberSummaryDto> memberSummaryDtos = allMembers.stream()
                .map(memberMapper::mapMemberToSummaryDto)
                .toList();
        return memberSummaryDtos;
    }
}
