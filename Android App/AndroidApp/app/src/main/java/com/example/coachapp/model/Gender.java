package com.example.coachapp.model;

public enum Gender {
    MALE("male", 1),
    FEMALE("female", 2),
    UNSURE("unsure", 3);

    private String stringValue;
    private int intValue;

    private Gender(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}