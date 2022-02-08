package com.example.coachapp.model;

public enum TrainingsLocation {
    GYM("Gym", 1),
    HOME("Home", 2),
    OUTDOOR("Outdoor",3 );

    private String stringValue;
    private int intValue;

    private TrainingsLocation(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
