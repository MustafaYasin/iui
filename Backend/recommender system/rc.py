from pymongo import MongoClient
import pandas as pd
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
collection = db['user']


users =  collection.find()


# Dataframe filled with users



## preprocessing

# 1. dummypersons als Dataframe in Pandas einlesen
df =  pd.DataFrame(list(users))

    # 2. Dataframe auf benoetigte features reduzieren --> level, workouts,     Am Ende soll der trainningsplan zu der Person noch bekannt sein --> bezug behalten

    # 3. normaliseren der features --> spalte [level]: beginner = 0 und [level] advanced =1. 
    #                              --> spalte [workouts]: durch max_workouts (6) teilen


## recommending

    # Die aehnlicheste Person mittels NearestNeighbour finden und deren Trainingsplan zurueckgeben.

    # input = new_person, 

    # Anzahl Nearest Neighbour Output 3 --> Mehrheitsentscheidung




new_person = {
    "workouts": 4,
    "level": "advanced",
    "age": 27,
    "sex": "male",
    "goal": "gain muscle",
}

recommended_output = 'tp_3'