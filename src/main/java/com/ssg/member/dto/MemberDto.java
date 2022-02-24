package com.ssg.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private String mbrId;
    private String mbrLoginId;
    private String mbrStatCd;

    @Builder
    public MemberDto(String mbrId, String mbrLoginId, String mbrStatCd) {
        this.mbrId = mbrId;
        this.mbrLoginId = mbrLoginId;
        this.mbrStatCd = mbrStatCd;
    }
}
