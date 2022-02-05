from pymongo import MongoClient
from bson.objectid import ObjectId 
# Create connection to MongoDB
client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
collection = db['ubungen']

# muscle subsets
arms_subset_muscle=['Forearms','Biceps','Triceps']
shoulder_subset_muscle=['Back shoulder', 'Lateral shoulder', 'Front shoulder', 'Side Shoulder','Posterior shoulder']
chest_subset_muscle=['Upper chest', 'Lower chest', 'Mid-chest', 'Saw muscle']
back_subset_muscle=['Upper back', 'Lower back']
core_subset_muscle=['Upper abdominal muscles', 'Lower abdominal muscles','Lateral abdominal muscles']
butt_subset_muscle=['Gluteus Maximus Butt','Big buttock muscle','Abductors']
legs_subset_muscle=['Calf','Thigh']


# find all entries with empty muscle group
empty_muscle_group = { "muscle_group": ""}
empty_muscle_group = collection.find(empty_muscle_group)

#all=collection.find()
#for x in all:
#    print(x['subset_muscles'], x['muscle_group'])
#check each exercise with empty muscle group and update missing value
for exercise in empty_muscle_group:
    print(exercise["subset_muscles"])
    if exercise["subset_muscles"] in arms_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Arms" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in shoulder_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Shoulder" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in chest_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Chest" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in back_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Back" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in core_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Core" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in butt_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Butt" } }
        collection.update_one(exercise,newvalues)
    elif exercise["subset_muscles"] in legs_subset_muscle:
        newvalues = { "$set": { "muscle_group": "Legs" } }
        collection.update_one(exercise,newvalues)
    