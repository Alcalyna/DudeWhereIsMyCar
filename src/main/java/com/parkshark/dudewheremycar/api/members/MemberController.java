
package com.parkshark.dudewheremycar.api.members;

import com.parkshark.dudewheremycar.service.members.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;

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
    public MemberDto registerMember(@RequestBody MemberDto memberDto){
        logger.info("Register member started");
        return memberService.registerMember(memberDto);
    }




}
