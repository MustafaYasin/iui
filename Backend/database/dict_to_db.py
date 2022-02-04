#simple example on howto transfer dict to mongodb with pymongo

from pymongo import MongoClient

# Create connection to MongoDB
client = MongoClient('localhost', 27017)
db = client['name_of_database']
collection = db['name_of_collection']

# Build a basic dictionary
d = {'website': 'www.carrefax.com', 'author': 'Daniel Hoadley', 'colour': 'purple'}

# Insert the dictionary into Mongo
collection.insert(d)
