package com.example.coachapp.model.unused;

public class TrainingsPlanSettings {
    private int weekLength;
    private int daysFrequency;
    private int maxTrainingsTime;
    private int cardio; // true: 1, false: 0
    private int weightTraining; // true: 1, false: 0

    public TrainingsPlanSettings() {
    }

    public int getWeekLength() {
        return weekLength;
    }

    public void setWeekLength(int weekLength) {
        this.weekLength = weekLength;
    }

    public int getDaysFrequency() {
        return daysFrequency;
    }

    public void setDaysFrequency(int daysFrequency) {
        this.daysFrequency = daysFrequency;
    }

    public int getMaxTrainingsTime() {
        return maxTrainingsTime;
    }

    public void setMaxTrainingsTime(int maxTrainingsTime) {
        this.maxTrainingsTime = maxTrainingsTime;
    }

    public int getCardio() {
        return cardio;
    }

    public void setCardio(int cardio) {
        this.cardio = cardio;
    }

    public int getWeightTraining() {
        return weightTraining;
    }

    public void setWeightTraining(int weightTraining) {
        this.weightTraining = weightTraining;
    }
}
