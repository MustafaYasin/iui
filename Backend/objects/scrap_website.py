import requests
from bs4 import BeautifulSoup
import re

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
    
    def exercise_title():
        return soup.find("h1", class_= "entry-title").text

    def muscle_image():
        return soup.find("img", class_="muskelbild").get('src')

    def equipment():
        return soup.find(string=re.compile("Benötigtes Equipment:")).parent.next_sibling.next_sibling.text

    def exercise_level():
        return soup.find(string=re.compile("Schwierigkeitsgrad:")).parent.next_sibling.next_sibling.text

    def target_muscles():
        return soup.find(string=re.compile("Zielmuskeln:")).parent.next_sibling.next_sibling.text

    def supporting_muscles():
        return soup.find(string=re.compile("Unterstützende Muskulatur:")).parent.next_sibling.next_sibling.text

    def synonym_titles():
        return soup.find(string=re.compile("Weitere Bezeichnungen:")).parent.next_sibling.next_sibling.text

    # make better solution to get whole text
    def exercise_description():
        description = soup.find(string=re.compile("Erklärung der Übung")).parent
        description_text = []

        sibling1 = description.next_sibling.next_sibling
        if sibling1.name != "div":
            description_text.append(sibling1.text)

        sibling2 = sibling1.next_sibling.next_sibling
        if sibling2.name != "div":
            description_text.append(sibling2.text)
        
        return description_text

    def exercise_execution():
        return ""

    def video_links():
        return ""

