
# Coach

Coach is an app which recommends you a workout plan and location based on your preferencess and location


## Screenshots

![App Screenshot](https://github.com/MustafaYasin/iui/blob/main/Interface/photo_2022-02-09%2016.06.37.jpeg)


## Documentation

This project was done as part of the IUI lecture at the LMU Munich. In order to successfully complete this project, 
the project has to meet the following requirements.

- **Voice Interface:** We implented a VI in the in andriod app, to communicate with the user
- **Recommender System:** Based on the information we are receiving from the user through the VI we recommend the User a Workout plan or location
- **Natural language Processing:** We are crawling workout data from the web then we summerize and tranlate it form DE to EN
- **Sensors:** Using GPS we are accessing user location and recommend a nearby workout place





## Installation

Install the backend/server requirements with Anaconda

```bash
  git clone https://github.com/MustafaYasin/iui

```
    
```bash
  cd iui
  Conda install pip
  pip install -r requirements.txt
  
```
    
## Usage

To run the Flask server
```bash
cd iui/Backend/recommender_system
python server.py
```


## API Reference

#### Get all items


```http
  GET /api/items/${id}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of item to fetch |
| `name`      | `string` | **Required**. Name of user |
| `age`      | `int` | **Required**. Age of user |
| `gender`      | `string` | **Required**. Gender of user |
| `workouts`      | `int` | **Required**. How many workouts a week |
| `experience`      | `int` | **Required**. How experienced is user |
| `trainingsGoal`      | `string` | **Required**. How fit does user want to be |
| `trainingsLocation`      | `string` | **Required**. Where does user want to train |







## Tech Stack

**Client:** Java: Android

**Server:** Python: Flask, Scrapy


## Roadmap

- Additional browser support

- Add more integrations


## Authors

- [@MustafaYasin](https://github.com/MustafaYasin)
- [@BarbaraSe](https://github.com/BarbaraSe)
- [@richardhofmann2711](https://github.com/richardhofmann2711)
- [@Sunny489](https://github.com/Sunny489)


