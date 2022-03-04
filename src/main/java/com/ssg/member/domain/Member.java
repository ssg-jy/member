package com.ssg.member.domain;

import com.ssg.member.domain.code.MbrStatCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mbrLoginId;

    @Column
    @Enumerated(EnumType.STRING)
    private MbrStatCode mbrStatCd;

    @Builder
    public Member(Long id, String mbrLoginId, MbrStatCode mbrStatCd) {
        this.id = id;
        this.mbrLoginId = mbrLoginId;
        this.mbrStatCd = mbrStatCd;
    }
}
