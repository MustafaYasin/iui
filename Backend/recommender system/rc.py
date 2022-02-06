from operator import ne
from pymongo import MongoClient
import pandas as pd
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
model = sklearn.neighbors.NearestNeighbors(n_neighbors=3, algorithm='brute', metric='cosine')
model.fit(recommender_frame[["workouts","level"]])

new_person = pd.DataFrame ({
    "workouts": [0.66],
    "level": [1],
})

distances, suggestions = model.kneighbors(new_person)
nearestNeighbour = recommender_frame.loc[suggestions[0,1]]
recommended_plan = nearestNeighbour.trainingplan
print(recommended_plan)
    # Die aehnlicheste Person mittels NearestNeighbour finden und deren Trainingsplan zurueckgeben.

    # input = new_person, 

    # Anzahl Nearest Neighbour Output 3 --> Mehrheitsentscheidung





recommended_output = 'tp_3'