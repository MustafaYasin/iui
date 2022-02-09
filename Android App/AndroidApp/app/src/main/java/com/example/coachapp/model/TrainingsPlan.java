package com.example.coachapp.model;

import java.util.ArrayList;

public class TrainingsPlan {
    private String title;
    private int trainings;
    private String level;
    private int split;
    private int sets;
    private ArrayList<TrainingsDay> trainingsDays;

    public TrainingsPlan(){
        this.trainingsDays = new ArrayList<>(0);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrainings() {
        return trainings;
    }

    public void setTrainings(int trainings) {
        this.trainings = trainings;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSplit() {
        return split;
    }

    public void setSplit(int split) {
        this.split = split;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public ArrayList<TrainingsDay> getTrainingsDays() {
        return trainingsDays;
    }

    public void setTrainingsDays(ArrayList<TrainingsDay> trainingsDays) {
        this.trainingsDays = trainingsDays;
    }

    public void addTrainingDay(TrainingsDay trainingsDay) {
        this.trainingsDays.add(trainingsDay);
    }
}
