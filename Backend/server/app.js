const express = require('express')
const app = express()

const PORT = 3000

app.listen(PORT, () => {
    console.log("Listening on port " + PORT)
})

app.use(express.json())

app.post('/spokenText', (req, res)=> {
    const newSpokenText = {
        spokenText: req.body.spokenText,
    }

    const test = "Hello you"
    const query = {spokenText: newSpokenText.spokenText}

    res.status(200).send(JSON.stringify(test))
})

app.post('/currentLocation', (req, res)=> {
    const currentLocation = {
        location: req.body.location
    }

    const test = "Hello you I got your location"
    const query = {location: currentLocation.location}

    res.status(200).send(JSON.stringify(test))
})



