const express = require('express')
const app = express()

const PORT = 3000

app.listen(PORT, () => {
    console.log("Listening on port " + PORT)
})

app.use(express.json())

app.post('/spokenText', (req, res)=> {
    console.log("start response")
    //const newSpokenText = {
    //    spokenText: req.body.spokenText,
    //}
    const test = "Gut und dir?"
    //const query = {spokenText: newSpokenText.spokenText}
    console.log("responde", test)

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



