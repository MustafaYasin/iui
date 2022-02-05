from pymongo import MongoClient
from dummypersons import *

# Create connection to MongoDB
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
collection = db['user']


# push data to database user
collection = db['user']
persons= [person_1, person_2, person_3, person_4, person_5, person_6, person_7, person_8, person_9, person_10, person_11, person_12]

for person in persons:
    collection.insert_one(person)

