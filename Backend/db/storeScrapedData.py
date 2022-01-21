import pymongo
from pymongo import MongoClient



# setting up and connect to mongodb databas
cluster = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = cluster["iui"]
collection = db["test"]

