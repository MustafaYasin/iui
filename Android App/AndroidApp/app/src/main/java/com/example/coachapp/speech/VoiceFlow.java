package com.example.coachapp.speech;

import android.app.Activity;
import android.util.Log;

import com.example.coachapp.R;
import com.example.coachapp.connection.RetrofitInstance;
import com.example.coachapp.helper.Text2Double;
import com.example.coachapp.model.Experience;
import com.example.coachapp.model.Gender;
import com.example.coachapp.model.TrainingsGoal;
import com.example.coachapp.model.TrainingsLocation;
import com.example.coachapp.model.User;

import java.util.Locale;


public class VoiceFlow {

    private Activity activity;
    private TextFromSpeech textFromSpeech;
    private SpeechFromText speechFromText;
    private User user = new User();
    private static VoiceFlow instance;

    private enum Step {NAME, AGE, GENDER, WORKOUTS, GOAL, LEVEL, LOCATION, FINISHED}

    ;
    private Step currentStep;

    //public VoiceFlow(Activity activity) {
    public VoiceFlow() {
        //this.activity = activity;
        //textFromSpeech = new TextFromSpeech(activity);
        //speechFromText = new SpeechFromText(activity, textFromSpeech);
        new RetrofitInstance();
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        textFromSpeech = new TextFromSpeech(activity);
        speechFromText = new SpeechFromText(activity, textFromSpeech);
    }

    public void initialSettings() {
        speechFromText.speakOut(this.activity.getString(R.string.voiceflow_greeting1));
        // currentStep = Step.NAME;
         currentStep = Step.FINISHED;
    }

    public static VoiceFlow getInstance() {
        if (instance == null) {
            instance = new VoiceFlow();
        }

        return instance;
    }

    public void parseSpokenText(String text) {
        Log.i("Recognized text:", text);
        Text2Double textParser;
        switch (currentStep) {
            case NAME:
                String name = text.replace("my name is ", "").replace("mein Name ist ", "");
                user.setName(name);
                currentStep = Step.AGE;
                speechFromText.speakOut(String.format(getText(R.string.voiceflow_greeting2), user.getName()) + " " + getText(R.string.voiceflow_ageQ));
                break;
            case AGE:
                textParser = new Text2Double();
                int age;
                try {
                    age = (int) textParser.parse(text);
                } catch (Exception e) {
                    age = 0;
                }
                if (age == 0) {
                    errorHandler(getText(R.string.voiceflow_ageQ));
                    return;
                }
                user.setAge(age);
                currentStep = Step.GENDER;
                speechFromText.speakOut(getText(R.string.voiceflow_sexQ));
                break;
            case GENDER:
                text = text.toLowerCase(Locale.ROOT);
                Gender gender = null;
                if (text.contains("female") || text.contains("e-mail")) {
                    gender = Gender.FEMALE;
                } else if (text.contains("male") || text.contains("mail")) {
                    gender = Gender.MALE;
                }

                if (gender == null) {
                    errorHandler(getText(R.string.voiceflow_sexQ));
                    return;
                }
                user.setGender(gender);
                currentStep = Step.WORKOUTS;
                speechFromText.speakOut(getText(R.string.voiceflow_workoutsQ));
                break;
            case WORKOUTS:
                String days = text.replace("days", "").replace("day", "").trim();
                textParser = new Text2Double();
                int workouts;
                try {
                    workouts = (int) textParser.parse(days);
                } catch (Exception e) {
                    workouts = 0;
                }
                if (workouts == 0 || workouts > 7) {
                    errorHandler(getText(R.string.voiceflow_workoutsQ));
                    return;
                }
                user.setWorkouts(workouts);
                currentStep = Step.GOAL;
                speechFromText.speakOut(getText(R.string.voiceflow_trainingsgoalQ));
                break;
            case GOAL:
                text = text.toLowerCase(Locale.ROOT);
                TrainingsGoal goal = null;
                if (text.contains("fit")) {
                    goal = TrainingsGoal.FIT;
                } else if (text.contains("fat")) {
                    goal = TrainingsGoal.REDUCE_BODYFAT;
                } else if (text.contains("weight") || text.contains("wales") || text.contains("wait")) {
                    goal = TrainingsGoal.REDUCE_WEIGHT;
                } else if (text.contains("muscle")) {
                    goal = TrainingsGoal.GAIN_MUSCLES;
                } else if (text.contains("cardio") || text.contains("kardio")) {
                    goal = TrainingsGoal.CARDIO;
                }
                if (goal == null) {
                    errorHandler(getText(R.string.voiceflow_trainingsgoalQ));
                    return;
                }

                user.setTrainingsGoal(goal);
                speechFromText.speakOut(getText(R.string.voiceflow_levelQ));
                currentStep = Step.LEVEL;
                break;
            case LEVEL:
                text = text.toLowerCase(Locale.ROOT);
                Experience xp = null;

                if (text.contains("beginner")) {
                    xp = Experience.BEGINNER;
                } else if (text.contains("advanced")) {
                    xp = Experience.ADVANCED;
                } else if (text.contains("expert")) {
                    xp = Experience.EXPERT;
                }

                if (xp == null) {
                    errorHandler(getText(R.string.voiceflow_levelQ));
                    return;
                }

                user.setExperience(xp);
                currentStep = Step.LOCATION;
                speechFromText.speakOut(getText(R.string.voiceflow_locationQ));
                break;
            case LOCATION:
                text = text.toLowerCase(Locale.ROOT);
                TrainingsLocation location = null;

                if (text.contains("gym")) {
                    location = TrainingsLocation.GYM;
                } else if (text.contains("home")) {
                    location = TrainingsLocation.HOME;
                } else if (text.contains("outdoor") || text.contains("auto")) {
                    location = TrainingsLocation.OUTDOOR;
                }

                if (location == null) {
                    errorHandler(getText(R.string.voiceflow_locationQ));
                    return;
                }

                user.setTrainingsLocation(location);
                currentStep = Step.FINISHED;
                speechFromText.speakOut(getText(R.string.voiceflow_thx));
                break;
            case FINISHED:
                user.setName("Horst");
                user.setAge(17);
                user.setGender(Gender.MALE);
                user.setWorkouts(3);
                user.setTrainingsGoal(TrainingsGoal.REDUCE_WEIGHT);
                user.setExperience(Experience.ADVANCED);
                user.setTrainingsLocation(TrainingsLocation.OUTDOOR);
                break;
        }

        Log.i("USER_NAME", user.getName());
        Log.i("USER_AGE", String.valueOf(user.getAge()));
        Log.i("USER_WORKOUTS", String.valueOf(user.getWorkouts()));
        Log.i("USER_Gender", String.valueOf(user.getGender()));
        Log.i("USER_GOAL", String.valueOf(user.getTrainingsGoal()));
        Log.i("USER_XP", String.valueOf(user.getExperience()));
        Log.i("USER_LOCATION", String.valueOf(user.getTrainingsLocation()));
    }

    private String getText(int stringnumber) {
        return this.activity.getString(stringnumber);
    }

    public User getUser() {
        return user;
    }

    private void errorHandler(String question) {
        speechFromText.speakOut(getText(R.string.voiceflow_sorry) + "  " + question);
    }


//    public void startVoice(String voiceTxt) {
//        try {
//            speechFromText.speakOut(voiceTxt);
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Log.e("VoiceFlow", "Can't sleep", e);
//        }
//    }

}
