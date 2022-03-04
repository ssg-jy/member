package com.ssg.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberShpLocDto {
    private String mbrId;
    private String isDefault;
    private String address;

    @Builder
    public MemberShpLocDto(String mbrId, String isDefault, String address) {
        this.mbrId = mbrId;
        this.isDefault = isDefault;
        this.address = address;
    }
}

