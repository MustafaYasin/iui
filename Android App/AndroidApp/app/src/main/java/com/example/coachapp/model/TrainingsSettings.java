package com.example.coachapp.model;

public class TrainingsSettings {

    private TrainingsLocation[] preferredTrainingsLocation;
    private String[] trainingsPreferenceGym;
    private TrainingsEquipment[] trainingsEquipment;
    private WeatherPreference[] weatherPreference;
    private CardioPreference[] cardioPreference;

    public TrainingsSettings(){
    }

    public TrainingsLocation[] getPreferredTrainingsLocation() {
        return preferredTrainingsLocation;
    }

    public void setPreferredTrainingsLocation(TrainingsLocation[] preferredTrainingsLocation) {
        this.preferredTrainingsLocation = preferredTrainingsLocation;
    }

    public String[] getTrainingsPreferenceGym() {
        return trainingsPreferenceGym;
    }

    public void setTrainingsPreferenceGym(String[] trainingsPreferenceGym) {
        this.trainingsPreferenceGym = trainingsPreferenceGym;
    }

    public TrainingsEquipment[] getTrainingsEquipment() {
        return trainingsEquipment;
    }

    public void setTrainingsEquipment(TrainingsEquipment[] trainingsEquipment) {
        this.trainingsEquipment = trainingsEquipment;
    }

    public WeatherPreference[] getWeatherPreference() {
        return weatherPreference;
    }

    public void setWeatherPreference(WeatherPreference[] weatherPreference) {
        this.weatherPreference = weatherPreference;
    }

    public CardioPreference[] getCardioPreference() {
        return cardioPreference;
    }

    public void setCardioPreference(CardioPreference[] cardioPreference) {
        this.cardioPreference = cardioPreference;
    }
}
