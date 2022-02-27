package com.ssg.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class MemberShpLoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long mbrId;

    @Column(columnDefinition = "TINYINT", length = 1)
    private int isDefault;

    @Column(length = 255)
    private String address;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public MemberShpLoc(Long mbrId, int isDefault, String address) {
        this.mbrId = mbrId;
        this.isDefault = isDefault;
        this.address = address;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}