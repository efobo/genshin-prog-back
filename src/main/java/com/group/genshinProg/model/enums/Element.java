package com.group.genshinProg.model.enums;

public enum Element {
    Anemo("Anemo"),
    Geo("Geo"),
    Electro("Electro"),
    Dendro("Dendro"),
    Hydro("Hydro"),
    Pyro("Pyro"),
    Cryo("Cryo"),
    ERROR("ERROR");


    private final String value;

    Element(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Element getElementByString(String str) {
        if (str.equals("Anemo")) return Anemo;
        if (str.equals("Geo")) return Geo;
        if (str.equals("Electro")) return Electro;
        if (str.equals("Dendro")) return Dendro;
        if (str.equals("Hydro")) return Hydro;
        if (str.equals("Pyro")) return Pyro;
        if (str.equals("Cryo")) return Cryo;
        else return ERROR;
    }

    @Override
    public String toString() {
        return value;
    }
}
