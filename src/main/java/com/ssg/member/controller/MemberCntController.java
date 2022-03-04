package com.ssg.member.controller;

import com.ssg.member.dto.MemberCntDto;
import com.ssg.member.dto.ResponseDto;
import com.ssg.member.service.MemberCntService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
public class MemberCntController {

    private final MemberCntService memberCntService;

    @GetMapping("/members/cnts/{mbrId}")
    public ResponseDto<MemberCntDto> getContact(@PathVariable Long mbrId) {
        try {
            ResponseDto<MemberCntDto> responseDto = new ResponseDto<>();
            responseDto.setData(memberCntService.findMemberCntById(mbrId));
            return responseDto;
        } catch (NoSuchElementException e) {
            return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "존재하지 않는 mbrContact", null);
        }
    }
}
