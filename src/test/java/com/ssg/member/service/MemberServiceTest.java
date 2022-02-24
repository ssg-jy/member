package com.ssg.member.service;

import com.ssg.member.domain.Member;
import com.ssg.member.domain.code.MbrStatCode;
import com.ssg.member.dto.MemberDto;
import com.ssg.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void find_mbr() {
        Member testMember = Member.builder()
                .mbrLoginId("test2")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();
        memberRepository.save(testMember);

        MemberDto found = memberService.findMemberById(testMember.getId());
        
        assertEquals(testMember.getId(), Long.parseLong(found.getMbrId()));
        assertEquals(testMember.getMbrStatCd().getValue(), found.getMbrStatCd());
        assertEquals(testMember.getMbrLoginId(), found.getMbrLoginId());
    }

    @Test
    void find_not_exist_mbr() {
        assertThrows(NoSuchElementException.class, () -> memberService.findMemberById(1L));
    }
}