from flask import Flask
from flask_restful import Api
from flask_cors import CORS
from rc import Recommendation


app = Flask(__name__, static_url_path='/static')
CORS(app)
api = Api(app)



api.add_resource(Recommendation, '/recommend')

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5001, debug=True)
