package com.sofkau.utils;

public enum Constantes {
    TOKEN("44529EAA288C4FD8BE4D49F8C950B5C9"),
    TYPE("application/soap+xml"),
    CODE("charset=UTF-8"),
    ACTION_CONVERT_VALUE("action=\"http://www.xignite.com/services/ConvertRealTimeValue\"");

    private final String value;

    Constantes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}