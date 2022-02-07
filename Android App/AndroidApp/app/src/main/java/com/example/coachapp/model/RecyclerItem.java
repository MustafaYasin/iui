package com.example.coachapp.model;

public class RecyclerItem {
    private String exerciseName;
    private String muscleName;

    public RecyclerItem(String exerciseName, String muscleName) {
        this.exerciseName = exerciseName;
        this.muscleName = muscleName;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }
}
