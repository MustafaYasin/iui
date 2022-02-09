package com.example.coachapp.model.enum_model;

public enum Experience {
    BEGINNER("Beginner", 1),
    ADVANCED("Advanced", 2),
    EXPERT("Expert", 3);

    private String stringValue;
    private int intValue;

    private Experience(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
