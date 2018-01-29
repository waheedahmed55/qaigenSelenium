package com.app.enums.hotwire;

public enum HotwireConfigKeys {

    URL("url");

    private final String desc;
    HotwireConfigKeys(String s) {
        desc = s;
    }
    public String toString() {
        return this.desc;
    }

}
