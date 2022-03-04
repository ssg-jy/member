package com.ssg.member.controller;

import com.ssg.member.domain.MemberShpLoc;
import com.ssg.member.repository.MemberShpLocRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MemberShpLocController {

    private final MemberShpLocRepository memberShpLocRepository;

    @GetMapping("/members/shpLoc/default/{mbrId}")
    public MemberShpLoc getMemberShpLoc(@PathVariable Long mbrId) {
        return memberShpLocRepository.findByMbrIdAndIsDefault(mbrId, 1);
    }

    @GetMapping("/members/shpLoc/{mbrId}")
    public List<MemberShpLoc> getAllMemberShpLoc(@PathVariable Long mbrId) {
        return memberShpLocRepository.findAllByMbrId(mbrId);
    }

    @PostMapping("/members/shpLoc")
    public void createMemberShpLoc(@RequestBody MemberShpLoc memberShpLoc) {
        if(memberShpLoc.getIsDefault() == 1) {
            memberShpLocRepository.updateIsDefault(memberShpLoc.getMbrId());
        }

        MemberShpLoc msl = new MemberShpLoc(memberShpLoc.getMbrId(), memberShpLoc.getIsDefault(), memberShpLoc.getAddress());

        memberShpLocRepository.save(msl);
    }

    @PatchMapping("/members/shpLoc/{id}")
    public void updateMemberShpLoc(@PathVariable Long id, @RequestBody MemberShpLoc memberShpLoc) {
        Optional<MemberShpLoc> location = memberShpLocRepository.findById(id);

        location.ifPresent(selectLocation -> {
            if(memberShpLoc.getIsDefault() == 1) {
                memberShpLocRepository.updateIsDefault(memberShpLoc.getMbrId());
            }
            selectLocation.setIsDefault(memberShpLoc.getIsDefault());
            selectLocation.setAddress(memberShpLoc.getAddress());

           memberShpLocRepository.save(selectLocation);
        });
    }

    @DeleteMapping("/members/shpLoc/{id}")
    public void deleteMemberShpLoc(@PathVariable Long id) {
        Optional<MemberShpLoc> location = memberShpLocRepository.findById(id);

        location.ifPresent(selectLocation -> {
            memberShpLocRepository.delete(selectLocation);
        });
    }
}