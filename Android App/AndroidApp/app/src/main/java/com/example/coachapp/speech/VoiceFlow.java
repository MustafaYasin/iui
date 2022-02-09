package com.example.coachapp.speech;

import android.app.Activity;
import android.util.Log;

import com.example.coachapp.R;
import com.example.coachapp.connection.RetrofitInstance;
import com.example.coachapp.connection.Routes;
import com.example.coachapp.helper.Text2Double;
import com.example.coachapp.location.GoogleMapsApp;
import com.example.coachapp.model.ExerciseExplanation;
import com.example.coachapp.model.Experience;
import com.example.coachapp.model.Gender;
import com.example.coachapp.model.TrainingsGoal;
import com.example.coachapp.model.TrainingsLocation;
import com.example.coachapp.model.User;

import java.util.Locale;
import java.util.UUID;


public class VoiceFlow {

    private Activity activity;
    private TextFromSpeech textFromSpeech;
    private SpeechFromText speechFromText;
    private User user = new User();
    private static VoiceFlow instance;
    private Boolean finished = false;
    private Routes routes;
    private String exercise;
    private String nearbyLocation;

    private enum Step {NAME, AGE, GENDER, WORKOUTS, GOAL, LEVEL, LOCATION, FINISHED, EXPLANATION, LOCATIONNEARBY}

    private Step currentStep;

    public VoiceFlow() {
        new RetrofitInstance();
        routes = Routes.getInstance();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
        textFromSpeech = new TextFromSpeech(activity);
        speechFromText = new SpeechFromText(activity, textFromSpeech);
    }

    public void initialSettings() {
        user.setId(generateUUID());
        speechFromText.speakOutAndRecord(this.activity.getString(R.string.voiceflow_greeting1), true);
        currentStep = Step.NAME;
        finished = false;
    }

    public void greeting() {
        currentStep = Step.FINISHED;
        finished = true;
        speechFromText.speakOutAndRecord(this.activity.getString(R.string.voiceflow_greeting3), true);
    }

    public static VoiceFlow getInstance() {
        if (instance == null) {
            instance = new VoiceFlow();
        }
        return instance;
    }

    public void parseSpokenText(String text) {
        text = text.toLowerCase();
        Log.i("Recognized text:", text);
        Text2Double textParser;
        final boolean yes = text.contains("yes") || text.contains("Yes") || text.contains("right");
        switch (currentStep) {
            case NAME:
                String name = text.replace("my name is ", "").replace("mein Name ist ", "");
                user.setName(name);
                currentStep = Step.AGE;
                speechFromText.speakOutAndRecord(String.format(
                        getText(R.string.voiceflow_greeting2), user.getName()) + " " + getText(R.string.voiceflow_ageQ), true);
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
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_sexQ), true);
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
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_workoutsQ), true);
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
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_trainingsgoalQ), true);
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
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_levelQ), true);
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
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_locationQ), true);
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
                finished = true;
                speechFromText.speakOutAndRecord(getText(R.string.voiceflow_thx), false);
                if (user.isCompleted()) {
                    finished = true;
                    routes.sendUser(user);
                    sleeper(1000);
//                    routes.loadTrainingsplan(user);
                }
                break;
            case FINISHED:
                if (text == null) {
                    errorHandler(getText(R.string.voiceflow_sorry));
                    return;
                } else {
                    if (text.contains("is the next")) {
                        nearbyLocation = text.split("next")[1];
                        if (nearbyLocation != null) {
                            currentStep = Step.LOCATIONNEARBY;
                            speechFromText.speakOutAndRecord("You want to know the nearby places of " + nearbyLocation + "?", true);
                        } else {
                            errorHandler(getText(R.string.voiceflow_sorry));
                        }
                    } else if (text.contains("execution") || text.contains("execute")) {
                        // How is the right execution of ...?
                        if (text.contains("execution")) {
                            exercise = text.split("of")[1];
                        } else if (text.contains("execute")) {
                            exercise = text.split("execute")[0];
                        }
                        if (exercise != null) {
                            currentStep = Step.EXPLANATION;
                            speechFromText.speakOutAndRecord("You want to have an explanation of the exercise " + exercise + "?", true);
                        } else {
                            errorHandler(getText(R.string.voiceflow_sorry));
                        }

                    } else if (text.contains("trainingsplan") || text.contains("training")) {
                        user.setId(generateUUID());
                        user.setName("Horst");
                        user.setAge(17);
                        user.setGender(Gender.MALE);
                        user.setWorkouts(3);
                        user.setTrainingsGoal(TrainingsGoal.REDUCE_WEIGHT);
                        user.setExperience(Experience.ADVANCED);
                        user.setTrainingsLocation(TrainingsLocation.OUTDOOR);
                        speechFromText.speakOutAndRecord("I will generate a trainingsplan. One moment please", false);
                        routes.loadTrainingsplan(user);
                        //routes.getTrainingsplan();
                        sleeper(6000);
                        speechFromText.speakOutAndRecord("You can see your trainingsplan at the section trainingsplan on your bottom navigation", false);
                    } else {
                        //errorHandler(getText(R.string.voiceflow_sorry));
                      /*  Log.i("USER_NAME", user.getName());
                        Log.i("USER_AGE", String.valueOf(user.getAge()));
                        Log.i("USER_WORKOUTS", String.valueOf(user.getWorkouts()));
                        Log.i("USER_Gender", String.valueOf(user.getGender()));
                        Log.i("USER_GOAL", String.valueOf(user.getTrainingsGoal()));
                        Log.i("USER_XP", String.valueOf(user.getExperience()));
                        Log.i("USER_LOCATION", String.valueOf(user.getTrainingsLocation()));*/
                    }
                }
                break;
            case EXPLANATION:
                currentStep = Step.FINISHED;
                if (yes) {
                    currentStep = Step.FINISHED;

                    routes.getExerciseExplanation(exercise, speechFromText);
                    sleeper(4000);
                    //System.out.println("ausgabe " + routes.exerciseExplanation.getExecution());
                    speechFromText.speakOutAndRecord("Place yourself under the barbell bar and place it on the rear shoulder muscles or on the hood muscle . Grab the bar significantly wider than shoulder width and push the elbows backwards . Lift the bar with straight torso and slightly hollow cross position in the lower back out of the mount",false);
                } else {
                    speechFromText.speakOutAndRecord("Please repeat", true);
                }
                break;
            case LOCATIONNEARBY:
                currentStep = Step.FINISHED;
                if (yes) {
                    speechFromText.speakOutAndRecord("I have marked the nearby " + nearbyLocation +
                            " in your Google Maps, which I will opened for you right now.", false);
                    sleeper(6000);
                    GoogleMapsApp googleMapsApp = new GoogleMapsApp(activity);
                    googleMapsApp.searchNearby(nearbyLocation);
                } else {
                    speechFromText.speakOutAndRecord("Please repeat", true);
                }
                break;
            default:
                errorHandler(getText(R.string.voiceflow_sorry));
                Log.e("VoiceFlow", "No case step availaible");
                break;
        }


    }

    private String getText(int stringnumber) {
        return this.activity.getString(stringnumber);
    }

    public User getUser() {
        return user;
    }

    private void errorHandler(String question) {
        speechFromText.speakOutAndRecord(question + "  " + question, true);
    }

    public Boolean getFinished() {
        return finished;
    }

    public void sleeper(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public UUID generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid;
    }
}
