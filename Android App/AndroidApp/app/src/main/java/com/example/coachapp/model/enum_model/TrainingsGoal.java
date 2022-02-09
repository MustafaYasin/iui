package com.example.coachapp.model.enum_model;

public enum TrainingsGoal {
    FIT("be fit", 0),
    REDUCE_BODYFAT("reduce bodyfat", 1),
    REDUCE_WEIGHT("reduce weight", 2),
    GAIN_MUSCLES("gain muscle", 3),
    CARDIO("better cardio", 4);

    private String stringValue;
    private int intValue;

    private TrainingsGoal(String toString, int value) {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}