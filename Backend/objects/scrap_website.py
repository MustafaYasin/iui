import requests
from bs4 import BeautifulSoup

import excercises
import muscleGroup


class ScrapWebsite:
    
    def __init__(self, url):
        self.url = url
        

    def call_website(url):
        main_url = "https://www.uebungen.ws/"
       
        page = requests.get(main_url + url)
        global soup 
        soup = BeautifulSoup(page.content, "html.parser")


    def category_description():
        return soup.find(id="kategorienbeschreibung").text


    def muscle_title():
        return soup.find("h1", class_= "page-title").text


    def subset_muscles():
        subset_muscles = []
        for item2 in soup.find_all("a", class_="katauswahlboxlink"):
            subset_muscles.append(item2.get("title"))
        return subset_muscles


    def list_of_excercises():
        excercises = []
        for item in soup.find_all(class_= "unterkategorie"):
            for item2 in item.find_all("article", class_="content-list"):
                title = item2.find("h3", class_="content-list-title")
                excercises.append(title.text)
        return excercises
    

