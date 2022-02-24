package com.ssg.member.domain.code;

public enum MbrStatCode {
    ACTIVE("10"),
    WITHDRAWL_ING("20"),
    WITHDRAWAL("30"),
    CONVERT("40"),
    DORMANT("50");

    private String value;

    MbrStatCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
