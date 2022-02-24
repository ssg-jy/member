package com.ssg.member.service;

import com.ssg.member.domain.Member;
import com.ssg.member.dto.MemberDto;
import com.ssg.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberDto findMemberById(Long mbrId) {
        Member member = memberRepository.findById(mbrId).orElseThrow(NoSuchElementException::new);
        return memberToDto(member);
    }

    private MemberDto memberToDto(Member member) {
        return MemberDto.builder()
                .mbrId(String.valueOf(member.getId()))
                .mbrLoginId(member.getMbrLoginId())
                .mbrStatCd(member.getMbrStatCd().getValue())
                .build();
    }
}
