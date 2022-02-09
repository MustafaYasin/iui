from pymongo import MongoClient
import pandas as pd
from flask_restful import Resource, reqparse

client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']
uebungen = db['ubungen']



def get_exercise(exercise):
    exercise=exercise['exercise']
    exercise_dict = uebungen.find_one({'exercise_title': exercise})
    exercise_dict.pop('_id')
    exercise_dict.pop('muscle_description_title')
    exercise_dict.pop('exercise_execution_title')
    return exercise_dict


def clean_exercise(exercise_dict):
    # taking important values out of dict, for combined dict
    for key, value in exercise_dict.items():
        filt = {
            'exercise_title': value['exercise_title'],
            'exercise_execution': value['exercise_execution'],
            'muscle_group': value['muscle_group'],
            'subset_muscles': value['subset_muscles'],
            'muscle_description': value['muscle_description']
        }
        exercise_dict[key] = filt
        print(exercise_dict)
        return exercise_dict

data2 = {"exercise": "Hip rolls"}
exercise_description=get_exercise(data2)
# for flask route

class Search_exercise(Resource):

    # incoming arguments
    def post(self):
        partner_feed_generator = reqparse.RequestParser()
        partner_feed_generator.add_argument('exercise', help='This field cannot be blank', required=True, type=str)
       

        input_exercise = partner_feed_generator.parse_args()

        # calling function which calls other functions,...
        exercise_description=get_exercise(input_exercise)

        return {'response': exercise_description}, 200
