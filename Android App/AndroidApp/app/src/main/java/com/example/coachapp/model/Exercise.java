package com.example.coachapp.model;

public class Exercise {
    private String title;
    private String execution;
    private String muscleGroup;
    private String subsetMuscles;
    private String muscleDescription;

    public Exercise() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecution() {
        return execution;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public void setMuscleGroup(String muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public String getSubsetMuscles() {
        return subsetMuscles;
    }

    public void setSubsetMuscles(String subsetMuscles) {
        this.subsetMuscles = subsetMuscles;
    }

    public String getMuscleDescription() {
        return muscleDescription;
    }

    public void setMuscleDescription(String muscleDescription) {
        this.muscleDescription = muscleDescription;
    }
}
