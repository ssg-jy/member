package com.ssg.member.service;

import com.ssg.member.domain.MemberCnt;
import com.ssg.member.domain.code.MbrStatCode;
import com.ssg.member.dto.MemberCntDto;
import com.ssg.member.repository.MemberCntRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberCntServiceTest {

    @Autowired
    MemberCntService memberCntService;
    @Autowired
    MemberCntRepository memberCntRepository;

    @Test
    void find_mbr() {
        MemberCnt testMember = MemberCnt.builder()
                .mbrContact("01076767676")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();
        memberCntRepository.save(testMember);

        MemberCntDto found = memberCntService.findMemberCntById(testMember.getId());

        assertEquals(testMember.getId(), Long.parseLong(found.getMbrId()));
        assertEquals(testMember.getMbrStatCd().getValue(), found.getMbrStatCd());
        assertEquals(testMember.getMbrContact(), found.getMbrContact());
    }

    @Test
    void find_not_exist_mbr() {
        assertThrows(NoSuchElementException.class, () -> memberCntService.findMemberCntById(1L));
    }
}
