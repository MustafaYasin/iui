from pymongo import MongoClient
import pandas as pd
from flask_restful import Resource, reqparse



client = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client['iui']

real_user_db = db['real_user']




def check_user_in_DB(input_user):
    # suche user mit id ... 
    existence = real_user_db.find_one({'id': input_user.id})

    # wenn existiert True, else False

    if existence == []:
        return False
    else:
        return True 

def add_user_to_db(input_user):

    if check_user_in_DB(input_user):
        done = 'user already exists in DB'
    else:
        real_user_db.insert(input_user)
        done = 'user succesfully added to DB'
    return done



# for flask route
class Add_user(Resource):

    # incoming arguments
    def post(self):
        partner_feed_generator = reqparse.RequestParser()
        partner_feed_generator.add_argument('id', help='This field cannot be blank', required=True, type=str)
        partner_feed_generator.add_argument('name', help='This field cannot be blank', required=True, type=str)                           
        partner_feed_generator.add_argument('age', help='This field cannot be blank', required=True, type=int)
        partner_feed_generator.add_argument('gender', help='This field cannot be blank', required=True, type=str)
        partner_feed_generator.add_argument('workouts', help='This field cannot be blank', required=True, type=int)
        partner_feed_generator.add_argument('experience', help='This field cannot be blank', required=False, type=str)
        partner_feed_generator.add_argument('trainingsGoal', help='This field cannot be blank', required=False, type=str)
        partner_feed_generator.add_argument('trainingsLocation', help='This field cannot be blank', required=False, type=str)

        input_user = partner_feed_generator.parse_args()

        
        done = add_user_to_db(input_user)

        return {'response': done}, 200