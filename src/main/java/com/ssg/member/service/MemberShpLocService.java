package com.ssg.member.service;

import com.ssg.member.domain.MemberShpLoc;
import com.ssg.member.dto.MemberShpLocDto;
import com.ssg.member.repository.MemberShpLocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberShpLocService {

    private final MemberShpLocRepository memberShpLocRepository;

    public MemberShpLocDto findMemberShpLocByMbrId(Long mbrId, int isDefault) {
        MemberShpLoc memberShpLoc = memberShpLocRepository.findByMbrIdAndIsDefault(mbrId, isDefault);
        return memberShpLocToDto(memberShpLoc);
    }

    private MemberShpLocDto memberShpLocToDto(MemberShpLoc memberShpLoc) {
        return MemberShpLocDto.builder()
                .mbrId(String.valueOf(memberShpLoc.getId()))
                .isDefault(String.valueOf(memberShpLoc.getIsDefault()))
                .address(memberShpLoc.getAddress())
                .build();
    }

//    public MemberShpLocDto createMemberShpLoc(Long mbrId, int isDefault, String address) {
//        MemberShpLoc memberShpLoc = memberShpLocRepository.createMemberShpLoc(mbrId, isDefault, address);
//        return memberShpLocToDto(memberShpLoc);
//    }
}
