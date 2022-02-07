from pymongo import MongoClient
import pandas as pd
import random
import pprint
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']

collection = db['user']

max_workout_days = 6
users =  collection.find()


## preprocessing

# 1. dummypersons als Dataframe in Pandas einlesen
df =  pd.DataFrame(list(users))

# 2. Dataframe auf benoetigte features reduzieren --> level, workouts,     Am Ende soll der trainningsplan zu der Person noch bekannt sein --> bezug behalten
recommender_frame = df[["workouts","level","trainingplan"]]

# 3.1 normaliseren der features --> spalte [level]: beginner = 0 und [level] advanced =1. 
recommender_frame.level[recommender_frame.level == 'beginner'] = 0
recommender_frame.level[recommender_frame.level == 'advanced'] = 1

# 3.2 normaliseren der features --> spalte [workouts]: durch max_workouts (6) teilen
recommender_frame.workouts = recommender_frame.workouts / max_workout_days
print(recommender_frame)


## recommending
import sklearn.neighbors

# use NearestNeighbour, get 3 nearest suggestions, falls mehr user kann man dann auf mehrheitsentscheidung umstellen
model = sklearn.neighbors.NearestNeighbors(n_neighbors=3, algorithm='brute', metric='cosine')

# fit model zu den beiden entscheidenden paramatern, erweiterbar bei mehr trainingsplaenen und mehr usern 
model.fit(recommender_frame[["workouts","level"]])

# example new person, richtige userdaten bekommt man von Frontend und filtert diese 2 features raus und macht preprocessing wie oben
new_person = pd.DataFrame ({
    "workouts": [0.66],
    "level": [1],
})

# liefert abstand und vorschlag der naechsten 3 neighbours
distances, suggestions = model.kneighbors(new_person)

# get closest user and save the trainingsplan title
nearestNeighbour = recommender_frame.loc[suggestions[0,1]]
recommended_plan = nearestNeighbour.trainingplan

print(recommended_plan)

# fetch recommended plan from db
trainingprogramms = db['trainingprogramm']

tp = trainingprogramms.find_one({'title': recommended_plan})
tp = pd.DataFrame.from_dict(tp)
df_chosen_exercises = pd.DataFrame()

# find some exercises for the muscle groups for the days
uebungen = db['ubungen']

# list of days to loop through areas and muscles
days = [tp.day_1,tp.day_2,tp.day_3,tp.day_4,tp.day_5,tp.day_6,tp.day_7]



# day_1
for day in days:
    # save muscles and number of exercises per muscle 
    muscles = day.area

    # helper for fixing writing inconsitency error shoulder
    i=0

    # checking for Rest day
    if not (muscles.capitalize() == 'Rest') and not (muscles.capitalize() == 'Cardio'):
        num_ex = day.exercises
        muscles = muscles.split(", ")

        # fixing different shoulder spellings --> taking away: verify/ fixing different inputs as early and has hard as possible
        for muscle in muscles:
            if (muscle.capitalize() == 'Shoulder'):
                muscles[i] = 'Shoulders'
            i=i+1

        # get random exercises for muscle
        for muscle in muscles:

            # find all exercises for specific muscle or muscle group --> depends on trainingsplan specifics
            exercises_for_muscle = uebungen.find({ "subset_muscles" : muscle.capitalize()})
            exercises_for_area = uebungen.find({ "muscle_group" : muscle.capitalize()})

            # format pymongo cursor vektor to pandas dataframe
            df_exercises_for_muscle =  pd.DataFrame(list(exercises_for_muscle))
            df_exercises_for_area =  pd.DataFrame(list(exercises_for_area))

            # put all exercises in one Dataframe for specific muscle group
            exercises_all = [df_exercises_for_area, df_exercises_for_muscle]
            exercises_all = pd.concat(exercises_all)
            
            # get number of exercises for specific muscle
            num_exercises = len(exercises_all)

            # get num_ex random int in range of num_exercises
            exercises = random.sample(range(0, num_exercises), num_ex)

            # select exercises 
            for exercise in exercises:
                exercise_frame = exercises_all.iloc[exercise].to_frame()
                df_chosen_exercises.append(exercise_frame)
                print(exercises_all.iloc[exercise].to_frame())
            # and store in output Structure for App
            #"exerxise_execution"
            #   "exercise_title"
print(df_chosen_exercises)


# todo's
# database cardio