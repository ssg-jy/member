package com.ssg.member.repository;

import com.ssg.member.domain.Member;
import com.ssg.member.domain.code.MbrStatCode;
import com.ssg.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void setUp() {
        Member testMember = Member.builder()
                .mbrLoginId("test")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();
        memberRepository.save(testMember);
    }

    @Test
    void findMember() {
        List<Member> all = memberRepository.findAll();
        assertEquals(1, all.size());

        Member member = all.get(0);
        assertEquals("test", member.getMbrLoginId());
        assertEquals(MbrStatCode.ACTIVE, member.getMbrStatCd());

    }

}