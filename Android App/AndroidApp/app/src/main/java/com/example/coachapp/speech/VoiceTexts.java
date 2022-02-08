package com.example.coachapp.speech;

import android.content.res.Resources;

import com.example.coachapp.R;

import kotlin.reflect.KFunction;

public class VoiceTexts {

    private String greeting1 = "Hi I am Coach Sam. What is your name?";
    private String greeting2 = "Hi nice to meet you. Thanks for using me as your coach. For your first use I have some questions for you.";
    private String ageQ = "How old are you?";
    private String sexQ = "Are you male or female?";
    private String workoutsQ = "How many days per week are you able to train?";
    private String trainingsgoalQ = "What is your trainingsgoal - be fit, reduce bodyfat, reduce, weight, gain muscles or better cardio?";
    private String levelQ = "Are you a beginner, advanced or expert at sport in general";
    private String locationQ = "Do you want to train at the gym, at home or outdoor?";
    private String thx = "Thank you! Let's set up your account.";
    private String sorry = "Sorry I did not get that.";

    public VoiceTexts() {
    }

    public String getGreeting1() {
        // return Resources.getSystem().getString(R.string.voiceflow_greeting1);
        return greeting1;
    }

    public String getGreeting2() {
        return greeting2;
    }

    public String getAgeQ() {
        return ageQ;
    }

    public String getSexQ() {
        return sexQ;
    }

    public String getWorkoutsQ() {
        return workoutsQ;
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

    public String getThx() {
        return thx;
    }

    public String getSorry() {
        return sorry;
    }
}
