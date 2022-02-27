package com.ssg.member.controller;

import com.ssg.member.dto.MemberDto;
import com.ssg.member.dto.ResponseDto;
import com.ssg.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/members/{mbrId}")
    public ResponseDto<MemberDto> getMember(@PathVariable Long mbrId) {
        try {
            ResponseDto<MemberDto> responseDto = new ResponseDto<>();
            responseDto.setData(memberService.findMemberById(mbrId));
            return responseDto;
        } catch (NoSuchElementException e) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 mbrId", null);
        }
    }
}
