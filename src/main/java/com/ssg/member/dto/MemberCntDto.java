package com.ssg.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberCntDto {
    private String mbrId;
    private String mbrContact;
    private String mbrContactCategory;
    private String mbrStatCd;


    @Builder
    public MemberCntDto(String mbrId, String mbrContact, String mbrContactCategory, String mbrStatCd) {
        this.mbrId = mbrId;
        this.mbrContact = mbrContact;
        this.mbrContactCategory = mbrContactCategory;
        this.mbrStatCd = mbrStatCd;
    }
}