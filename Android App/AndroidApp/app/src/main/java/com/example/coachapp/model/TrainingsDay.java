package com.example.coachapp.model;

import java.util.ArrayList;

public class TrainingsDay {
    private String area;
    private int exerciseCount;
    private ArrayList<Exercise> exercises;

    public TrainingsDay() {
        this.exercises = new ArrayList<>(0);
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getExerciseCount() {
        return exerciseCount;
    }

    public void setExerciseCount(int exerciseCount) {
        this.exerciseCount = exerciseCount;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }
}
