from http.server import BaseHTTPRequestHandler
from routes import routes
from cgi import parse_header, parse_multipart
from urllib.parse import parse_qs, urlparse
import json

class Server(BaseHTTPRequestHandler):
  def do_HEAD(self):
    return
    
 # def do_GET(self):
 #   # self.respond()
 #   # if self.path == "/spokenText":
 #   #     self.send_response(200)
 #   #     self.end_headers()
 #   #     self.wfile.write(b'Test!')
 #   return

  def do_GET(self):
    
    # self.respond()
    parsed_path = urlparse(self.path)
    print(parsed_path)
    if parsed_path.path == "/user":
        self.send_response(200)
        self.end_headers()
        queryComponents = dict(qc.split("=") for qc in parsed_path.query.split("&"))
        objectId = queryComponents["user"]
        # @todo: db abruf
        if objectId == "61fe81456346143efeff9c89":
          user = {
            "objectId": "61fe81456346143efeff9c89", 
            "id": "test", 
            "name": "test", 
            "age": "26", 
            "gender": "male",
            "workouts": "3", 
            "experience": "beginner", 
            "trainingsGoal": "gain muscles", 
            "trainingsLocation": "home"
          }
        else:
          user = {}
        
        self.wfile.write(json.dumps(user).encode('utf-8'))
    return
    
  def do_POST(self):
    if self.path == "/spokenText":
        self.send_response(200)
        self.end_headers()
        self.wfile.write(json.dumps({"key" :"Hallo zurück!", "key2": "blaaaa"}).encode('utf-8'))
        print("in spokenText")
        postvars = self.parse_POST()
        print(postvars)
        print(postvars['mySpokenText'][0])

    if self.path == "/user":
        self.send_response(200)
        self.end_headers()
        
        print("in user")
        postvars = self.parse_POST()
        print(postvars)
        # @todo: db save
        user = json.dumps({
          "objectId": "61fe81456346143efeff9c89",
          "id": postvars["name"][0],
          "name" :postvars["name"][0],
          "age": postvars["age"][0],
          "gender": postvars["gender"][0],
          "workouts": postvars["workouts"][0],
          "experience": postvars["experience"][0],
          "trainingsGoal": postvars["trainingsGoal"][0],
          "trainingsLocation": postvars["trainingsLocation"][0]
        }).encode('utf-8')
        self.wfile.write(user)
        print("in do_POST")

    return
  
  def parse_POST(self):
    ctype, pdict = parse_header(self.headers['content-type'])
    if ctype == 'multipart/form-data':
        postvars = parse_multipart(self.rfile, pdict)
    elif ctype == 'application/x-www-form-urlencoded':
        length = int(self.headers['content-length'])
        postvars = parse_qs(
                self.rfile.read(length).decode('utf-8'), 
                keep_blank_values=1)
    else:
        postvars = {}
    return postvars
    
  def handle_http(self, status, content_type):
    self.send_response(status)
    self.send_header('Content-type', content_type)
    self.end_headers()


    route_content = routes[self.path]
    return bytes(route_content, "UTF-8")
    
  def respond(self):
    content = self.handle_http(200, 'text/html')
    self.wfile.write(content)