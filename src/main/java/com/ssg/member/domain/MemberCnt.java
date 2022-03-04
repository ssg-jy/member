package com.ssg.member.domain;

import com.ssg.member.domain.code.MbrStatCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class MemberCnt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String mbrContact;

    @Column
    private String mbrContactCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private MbrStatCode mbrStatCd;

    @Builder
    public MemberCnt(Long id, String mbrContact, String mbrContactCategory, MbrStatCode mbrStatCd) {
        this.id = id;
        this.mbrContact = mbrContact;
        this.mbrContactCategory = mbrContactCategory;
        this.mbrStatCd = mbrStatCd;
    }
}