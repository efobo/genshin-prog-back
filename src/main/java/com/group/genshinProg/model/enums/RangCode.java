package com.group.genshinProg.model.enums;

public enum RangCode {

    THREE_STAR("THREE_STAR"),
    FOUR_STAR("FOUR_STAR"),
    FIVE_STAR("FIVE_STAR"),
    ERROR("ERROR");

    private final String value;

    RangCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RangCode getRangCodeByString(String str) {
        if (str.equals("THREE_STAR")) return THREE_STAR;
        if (str.equals("FOUR_STAR")) return FOUR_STAR;
        if (str.equals("FIVE_STAR")) return FIVE_STAR;
        else return ERROR;
    }

    @Override
    public String toString() {
        return value;
    }
}
