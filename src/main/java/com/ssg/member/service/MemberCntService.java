package com.ssg.member.service;

import com.ssg.member.domain.MemberCnt;
import com.ssg.member.dto.MemberCntDto;
import com.ssg.member.repository.MemberCntRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberCntService {

    private final MemberCntRepository memberCntRepository;

    public MemberCntDto findMemberCntById(Long mbrId) {
        MemberCnt memberCnt = memberCntRepository.findById(mbrId).orElseThrow(NoSuchElementException::new);
        return contactToDto(memberCnt);
    }

    private MemberCntDto contactToDto(MemberCnt memberCnt) {
        return MemberCntDto.builder()
                .mbrId(String.valueOf(memberCnt.getId()))
                .mbrContact(String.valueOf(memberCnt.getMbrContact()))
                .mbrContactCategory(String.valueOf(memberCnt.getMbrContactCategory()))
                .mbrStatCd(memberCnt.getMbrStatCd().getValue())
                .build();
    }
}
