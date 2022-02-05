from pymongo import MongoClient
from trainingsplan import *

# Create connection to MongoDB
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
collection = db['user']


# push data to database trainingsprogramm
collection = db['trainingprogramm']
trainingsprogramms = [trainingplan_1, trainingplan_2, trainingplan_3, trainingplan_4, trainingplan_5, trainingplan_6]

for trainingsprogramm in trainingsprogramms:
    collection.insert_one(trainingsprogramm)