
package com.parkshark.dudewheremycar.service.members;

import com.parkshark.dudewheremycar.api.members.MemberController;
import com.parkshark.dudewheremycar.api.members.MemberDto;
import com.parkshark.dudewheremycar.api.members.MemberMapper;
import com.parkshark.dudewheremycar.domain.members.Member;
import com.parkshark.dudewheremycar.repository.members.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private Logger logger = LoggerFactory.getLogger(MemberService .class);

    @Autowired
    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto registerMember(MemberDto memberDto){
        Member member = memberMapper.mapDtoToMember(memberDto);
        logger.info("Member created");
        MemberDto outputMemberDto = memberMapper.mapMemberToDto(memberRepository.save(member));
        logger.info("Member saved");
        return outputMemberDto;
    }
}
