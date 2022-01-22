var express = require('express');
var app = express();
var host = 8001

app.get('/', function (req, res) {
   res.send('Hello World');
})

var server = app.listen(host, function () {
   var host = server.address().address
   var port = server.address().port
   
   console.log("Example app listening at http://%s:%s", host, port)
})