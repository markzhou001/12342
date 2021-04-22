package com.code.config;


public enum WeekDays {

    MONDAY("MONDAY","星期一"),
    TUESDAY("TUESDAY","星期二"),
    WEDNESDAY("WEDNESDAY","星期三"),
    THURSDAY("THURSDAY","星期四"),
    FRIDAY("FRIDAY","星期五"),
    SATURDAY("SATURDAY","星期六"),
    SUNDAY("SUNDAY","星期日");


    private String value;
    private String description;

    WeekDays(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }
}