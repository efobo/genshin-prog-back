package com.group.genshinProg.model.enums;

public enum WeaponType {
    Sword ("Sword"),
    Claymore("Claymore"), // двуручный меч
    Polearm("Polearm"), // древковое оружие
    Catalyst("Catalyst"),
    Bow("Bow"),
    ERROR("ERROR");

    private final String value;

    WeaponType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WeaponType getWeaponTypeByString(String str) {
        if (str.equals("Sword")) return Sword;
        if (str.equals("Claymore")) return Claymore;
        if (str.equals("Polearm")) return Polearm;
        if (str.equals("Catalyst")) return Catalyst;
        if (str.equals("Bow")) return Bow;
        else return ERROR;
    }

    @Override
    public String toString() {
        return value;
    }
}
