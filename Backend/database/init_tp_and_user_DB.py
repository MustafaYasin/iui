from pymongo import MongoClient
from trainingsplan import *
from dummypersons import *


# Create connection to MongoDB
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']

# push data to database user
users = db['user']
persons= [person_1, person_2, person_3, person_4, person_5, person_6, person_7, person_8, person_9, person_10, person_11, person_12]

for person in persons:
    users.insert_one(person)


# push data to database trainingsprogramm
trainingplans = db['trainingprogramm']
trainingsprogramms = [trainingplan_1, trainingplan_2, trainingplan_3, trainingplan_4, trainingplan_5, trainingplan_6]

for trainingsprogramm in trainingsprogramms:
    trainingplans.insert_one(trainingsprogramm)