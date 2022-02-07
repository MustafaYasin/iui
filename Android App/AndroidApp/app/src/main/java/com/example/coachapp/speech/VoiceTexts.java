package com.example.coachapp.speech;

public class VoiceTexts {

    private String greeting1 = "Hi I am Coach Sam. What is your name?";
    private String greeting2 = "Hi nice to meet you. Thanks for using me as your coach. For your first use I have some questions for you.";
    private String trainingsgoalQ = "What is your trainingsgoal?";
    private String levelQ = "Are you a beginner, advanced or expert at sport in general";
    private String locationQ = "Do you want to train at the gym, at home or outdoor?";


    public VoiceTexts(){
    }

    public String getGreeting1() {
        return greeting1;
    }

    public String getGreeting2() {
        return greeting2;
    }

    public String getTrainingsgoalQ() {
        return trainingsgoalQ;
    }

    public String getLevelQ() {
        return levelQ;
    }

    public String getLocationQ() {
        return locationQ;
    }
}
