
package com.parkshark.dudewheremycar.api.members;

import com.parkshark.dudewheremycar.service.members.MemberDto;
import com.parkshark.dudewheremycar.service.members.MemberService;
import com.parkshark.dudewheremycar.service.members.MemberSummaryDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final Logger logger = LoggerFactory.getLogger(MemberController.class);

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(consumes = "application/json" )
    @ResponseStatus(HttpStatus.CREATED)
    //@PreAuthorize("hasAuthority('CREATE_MEMBER_PARKSHARK')")
    public MemberDto registerMember(@RequestBody MemberDto memberDto){
        logger.info("Register member started");
        MemberDto registerdMemberDto = memberService.registerMember(memberDto);
        logger.info("Register member finished");
        return registerdMemberDto;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    //@PreAuthorize("hasAuthority('GET_ALL_MEMBERS_PARKSHARK')")
    public List<MemberSummaryDto> getAllMembers(){
        logger.info("Get all members started");
        List<MemberSummaryDto> memberSummaryDtoList = memberService.getAllMembers();
        logger.info("Get all members finished");
        return memberSummaryDtoList;
    }

}
