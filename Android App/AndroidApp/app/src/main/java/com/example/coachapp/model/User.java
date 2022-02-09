package com.example.coachapp.model;

public class User {
    private String name;
    private int age;
    private Gender gender;
    private int workouts;
    private int weight;
    private int height;
    private Experience experience;
    private TrainingsGoal trainingsGoal;
    private TrainingsLocation trainingsLocation;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Experience getExperience() {
        return experience;
    }

    public void setExperience(Experience experience) {
        this.experience = experience;
    }

    public TrainingsGoal getTrainingsGoal() {
        return trainingsGoal;
    }

    public void setTrainingsGoal(TrainingsGoal trainingsGoal) {
        this.trainingsGoal = trainingsGoal;
    }

    public TrainingsLocation getTrainingsLocation() {
        return trainingsLocation;
    }

    public void setTrainingsLocation(TrainingsLocation trainingsLocation) {
        this.trainingsLocation = trainingsLocation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkouts() {
        return workouts;
    }

    public void setWorkouts(int workouts) {
        this.workouts = workouts;
    }

    public boolean isCompleted() {
        return name != null
                && age != 0
                && gender != null
                && workouts != 0
                && experience != null
                && trainingsGoal != null
                && trainingsLocation != null;
    }
}
