package com.ssg.member.config;

import com.ssg.member.domain.Member;
import com.ssg.member.domain.MemberShpLoc;
import com.ssg.member.domain.MemberCnt;
import com.ssg.member.domain.code.MbrStatCode;
import com.ssg.member.repository.MemberCntRepository;
import com.ssg.member.repository.MemberRepository;
import com.ssg.member.repository.MemberShpLocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Profile(value = "default | dev")
public class InitData {

    private final MemberRepository memberRepository;

    private final MemberShpLocRepository memberShpLocRepository;
    private final MemberCntRepository memberCntRepository;

    @PostConstruct
    public void init() {
        Member testMember1 = Member.builder()
                .mbrLoginId("test1")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();

        Member testMember2 = Member.builder()
                .mbrLoginId("test2")
                .mbrStatCd(MbrStatCode.DORMANT)
                .build();

        MemberShpLoc testLoc1 = MemberShpLoc.builder()
                .mbrId(1L)
                .isDefault(1)
                .address("test1 기본 배송지").build();

        MemberShpLoc testLoc2 = MemberShpLoc.builder()
                .mbrId(1L)
                .isDefault(0)
                .address("test1 배송지").build();

        MemberShpLoc testLoc3 = MemberShpLoc.builder()
                .mbrId(2L)
                .isDefault(1)
                .address("test2 기본 배송지").build();

        MemberShpLoc testLoc4 = MemberShpLoc.builder()
                .mbrId(2L)
                .isDefault(0)
                .address("test2 배송지").build();


        memberRepository.save(testMember1);
        memberRepository.save(testMember2);

        memberShpLocRepository.save(testLoc1);
        memberShpLocRepository.save(testLoc2);
        memberShpLocRepository.save(testLoc3);
        memberShpLocRepository.save(testLoc4);
        MemberCnt testMemberCnt1 = MemberCnt.builder()
                .mbrContact("01076766666")
                .mbrContactCategory("home")
                .mbrStatCd(MbrStatCode.ACTIVE)
                .build();

        MemberCnt testMemberCnt2 = MemberCnt.builder()
                .mbrContact("01076769999")
                .mbrContactCategory("phone")
                .mbrStatCd(MbrStatCode.DORMANT)
                .build();

        memberCntRepository.save(testMemberCnt1);
        memberCntRepository.save(testMemberCnt2);
    }

}

