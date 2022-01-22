from pymongo import MongoClient
import Backend








# setting up and connect to mongodb databas

cluster = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = cluster["iui"]
collection = db["test"]


# adding first data to the database
firstData = collection.insert_many({"name": "mustafa", "age": "24"})

url = "schultern"

test = ScrapWebsite(url)
test.call_website(url)
print(test.list_of_exercises())