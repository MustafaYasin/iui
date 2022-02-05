from pymongo import MongoClient
from bson.objectid import ObjectId 
# Create connection to MongoDB
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
collection = db['user']


myquery = { '_id': ObjectId('61fe81456346143efeff9c8b') }
newvalues = { "$set": { "id": 13 } }
mydoc = collection.find(myquery)


