package com.dailystar.dsacademy.util.enums;

public enum SearchFieldType
{
    LOCALDATETIME("LocalDateTime"),
    LOCALDATE("LocalDate"),
    STRING("String"),
    INTEGER("Integer"),
    INT("Int"),
    DOUBLE("Double"),
    BOOLEAN("Boolean");

    private final String code;

    SearchFieldType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
