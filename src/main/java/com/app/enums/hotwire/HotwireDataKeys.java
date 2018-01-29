package com.app.enums.hotwire;

public enum HotwireDataKeys {

    FROM_CITY ("fromCity"),
    TO_CITY ("toCity");

    private final String desc;
    HotwireDataKeys(String s) {
        desc = s;
    }
    public String toString() {
        return this.desc;
    }
}
