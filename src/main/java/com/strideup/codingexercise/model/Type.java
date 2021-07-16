package com.strideup.codingexercise.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {

    Physical("Physical"),
    Mailing("Mailing"),
    Voice("Voice"),
    Fax("Fax"),
    TTY("TTY");

    private final String str;

    Type(String str) {
        this.str = str;
    }
}
