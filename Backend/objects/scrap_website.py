import requests
from bs4 import BeautifulSoup

import exercise
import muscle_group


class ScrapWebsite:
    
    def call_website(url):
        main_url = "https://www.uebungen.ws/"
        page = requests.get(main_url + url)
        global soup 
        soup = BeautifulSoup(page.content, "html.parser")

    def muscle_description():
        return soup.find(id="kategorienbeschreibung").text

    def muscle_title():
        return soup.find("h1", class_= "page-title").text

    def subset_muscles():
        subset_muscles = []
        for item2 in soup.find_all("a", class_="katauswahlboxlink"):
            subset_muscles.append(item2.get("title"))
        return subset_muscles

    def list_of_exercises():
        exercise = []
        for item in soup.find_all(class_= "unterkategorie"):
            for item2 in item.find_all("article", class_="content-list"):
                title = item2.find("h3", class_="content-list-title")
                exercise.append(title.text)
        return exercise
    
    def list_of_sub_exercises():
        sub_exercises = []
        for item in soup.find_all(class_= "content-list"):
            title = item.find("h3", class_="content-list-title")
            sub_exercises.append(title.text)
        return sub_exercises
    

