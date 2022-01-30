package com.example.coachapp.model;

public class User {
    private String name;
    private int age;
    private int weight;
    private int height;
    private Experience experience;
    private String trainingsGoal;

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getTrainingsGoal() {
        return trainingsGoal;
    }

    public void setTrainingsGoal(String trainingsGoal) {
        this.trainingsGoal = trainingsGoal;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
