const express = require('express')
const app = express()
const mongoClient = require('mongodb').MongoClient

const PORT = 3000
const URL = "mongodb://localhost:27017"

app.use(express.json())

mongoClient.connect(URL, (err, db)=>{
    if (err) {
        console.log("Error while connecting to mongo client")
    } else {
        const myDb = db.db('myDb')
        const collection = myDb.collection('myTable')

        app.post('/spokenText', (req, res)=> {
            const newSpokenText = {
                spokenText: req.body.spokenText,
                location: req.body.location
            }

            const test = "Hello you"

            const query = {spokenText: newSpokenText.spokenText}

            collection.findOne(query, (err, result) => {
                if(result == null) {
                    collection.insertOne(newSpokenText, (err, result) => {
                        res.status(200).send()
                    })
                    res.status(200).send(JSON.stringify(newSpokenText))
                } else {
                    res.status(400).send()
                }
            })
        })
    }
})

app.listen(PORT, () => {
    console.log("Listening on port " + PORT)
})

