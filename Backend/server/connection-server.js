import routes from routes

const http = require("http");
const host = 'localhost';
const port = 8000;


const server = http.createServer(routes.requestListener);
server.listen(port, host, () => {
    console.log(`Server is running on http://${host}:${port}`);
});