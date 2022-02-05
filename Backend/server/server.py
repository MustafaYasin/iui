from http.server import BaseHTTPRequestHandler
from routes import routes
import json

class Server(BaseHTTPRequestHandler):
  def do_HEAD(self):
    return
    
  def do_GET(self):
    # self.respond()
    # if self.path == "/spokenText":
    #     self.send_response(200)
    #     self.end_headers()
    #     self.wfile.write(b'Test!')
    return
    
  def do_POST(self):
    if self.path == "/spokenText":
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b'Blubberdiblubb?')
        print("in spokenText")

    if self.path == "/user":
        content_length = int(self.headers['Content-Length'])
        body = self.rfile.read(content_length)
        json.dumps(newUser1 = {
            "name": self.rfile.name
            # "age": body.age
            # "weight": body.weight,
            # "height": body.height,
            # "experience": body.experience,
            # "trainingsGoal": body.trainingsGoal
        })
        print("in do_POST")

    return
    
  def handle_http(self, status, content_type):
    self.send_response(status)
    self.send_header('Content-type', content_type)
    self.end_headers()


    route_content = routes[self.path]
    return bytes(route_content, "UTF-8")
    
  def respond(self):
    content = self.handle_http(200, 'text/html')
    self.wfile.write(content)