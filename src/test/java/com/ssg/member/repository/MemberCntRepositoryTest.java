package com.ssg.member.repository;

import com.ssg.member.domain.MemberCnt;
import com.ssg.member.domain.code.MbrStatCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberCntRepositoryTest {

    @Autowired
    MemberCntRepository memberCntRepository;

    @BeforeEach
    public void setUp() {
        MemberCnt testMember = MemberCnt.builder()
                .mbrContact("01076767676")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();
        memberCntRepository.save(testMember);
    }

    @Test
    void findMember() {
        List<MemberCnt> all = memberCntRepository.findAll();
        assertEquals(1, all.size());

        MemberCnt MemberCnt = all.get(0);
        assertEquals("01076767676", MemberCnt.getMbrContact());
        assertEquals(MbrStatCode.ACTIVE, MemberCnt.getMbrStatCd());

    }

}