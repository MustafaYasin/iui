const express = require('express')
const app = express()

const PORT = 3000

app.listen(PORT, () => {
    console.log("Listening on port " + PORT)
})

app.use(express.json())

app.post('/spokenText', (req, res)=> {
    console.log("start response")
/*
    // gets input
    const newSpokenText = {
        spokenText: req.body.spokenText,
    }
    */
    
   // console.log("req Body", req.body);
    //console.log("newspokentext", req.body.spokenText);
    const test = "Test"
    console.log(test)

    // ToDo: check input text, send right response
    // response variable
    if(req.body == "Hello"){
        const response = "You are a maschine!!!"
    }
    //const response = "Blubberdiblubb?"
    const response = "neue antwort?"

    console.log("responde", response)
    res.status(200).send(JSON.stringify(response))
})


app.get('/spokenText/text', (req, res, next) =>{
    console.log("req get", req.params.text);
})

app.post('/user', (req, res)=> {
    const newUser = {
        name: req.body.name,
        age: req.body.age,
        weight: req.body.weight,
        height: req.body.height,
        experience: req.body.experience,
        trainingsGoal: req.body.trainingsGoal
    }

    // Todo: save in database
    // send result of database:
//    if() {
//        res.status(200).send()
//    } else {
//        res.status(400).send()
//    }
})

app.post('/trainingsSettings', (req, res)=> {
    const newTrainingsSettings = {
        preferredTrainingsLocation: req.body.preferredTrainingsLocation,
        trainingsPreferenceGym: req.body.trainingsPreferenceGym,
        trainingsEquipment: req.body.trainingsEquipment,
        weatherPreference: req.body.weatherPreference,
        experience: req.body.experience,
        cardioPreference: req.body.cardioPreference
    }

    // Todo: save in database
    // send result of database:
//    if() {
//        res.status(200).send()
//    } else {
//        res.status(400).send()
//    }
})

app.post('/trainingsPlanSettings', (req, res)=> {
    const newTrainingsSettings = {
        weekLength: req.body.weekLength,
        daysFrequency: req.body.daysFrequency,
        maxTrainingsTime: req.body.maxTrainingsTime,
        cardio: req.body.cardio,
        weightTraining: req.body.weightTraining,
    }

    // Todo: save in database
    // send result of database:
//    if() {
//        res.status(200).send()
//    } else {
//        res.status(400).send()
//    }
})

