import requests
from bs4 import BeautifulSoup
import re


class ScrapWebsite:

    def __init__(self, url):
        self.url = url
    
    def call_website(self, url):
        main_url = "https://www.uebungen.ws/"
        page = requests.get(main_url + url)
        global soup 
        soup = BeautifulSoup(page.content, "html.parser")

    def muscle_description(self):
        return soup.find(id="kategorienbeschreibung").text

    def muscle_title(self):
        return soup.find("h1", class_= "page-title").text

    def subset_muscles(self):
        subset_muscles = []
        for item2 in soup.find_all("a", class_="katauswahlboxlink"):
            subset_muscles.append(item2.get("title"))
        return subset_muscles

    def list_of_exercises(self):
        exercise = []
        for item in soup.find_all(class_= "unterkategorie"):
            for item2 in item.find_all("article", class_="content-list"):
                title = item2.find("h3", class_="content-list-title")
                exercise.append(title.text)
        return exercise
    
    def list_of_sub_exercises(self):
        sub_exercises = []
        for item in soup.find_all(class_= "content-list"):
            title = item.find("h3", class_="content-list-title")
            sub_exercises.append(title.text)
        return sub_exercises
    
    def exercise_title(self):
        return soup.find("h1", class_= "entry-title").text

    def muscle_image(self):
        return soup.find("img", class_="muskelbild").get('src')

    def equipment(self):
        return soup.find(string=re.compile("Benötigtes Equipment:")).parent.next_sibling.next_sibling.text

    def exercise_level(self):
        return soup.find(string=re.compile("Schwierigkeitsgrad:")).parent.next_sibling.next_sibling.text

    def target_muscles(self):
        return soup.find(string=re.compile("Zielmuskeln:")).parent.next_sibling.next_sibling.text

    def supporting_muscles(self):
        return soup.find(string=re.compile("Unterstützende Muskulatur:")).parent.next_sibling.next_sibling.text

    def synonym_titles(self):
        return soup.find(string=re.compile("Weitere Bezeichnungen:")).parent.next_sibling.next_sibling.text

    # make better solution to get whole text
    def exercise_description(self):
        description = soup.find(string=re.compile("Erklärung der Übung")).parent
        description_text = []

        sibling1 = description.next_sibling.next_sibling
        if sibling1.name == "p":
            description_text.append(sibling1.text)

        sibling2 = sibling1.next_sibling.next_sibling
        if sibling2.name == "p":
            description_text.append(sibling2.text)

        description_text = list(filter(None, description_text))
        return "\n".join(description_text)
        
 

    def exercise_execution(self):
        execution = soup.find(string=re.compile("Die richtige Ausführung")).parent
        execution_text = []

        sibling1 = execution.next_sibling.next_sibling
        if sibling1.name != "div":
            execution_text.append(sibling1.text)

        sibling2 = sibling1.next_sibling.next_sibling
        if sibling2.name != "div":
            execution_text.append(sibling2.text)
        
        sibling3 = sibling2.next_sibling.next_sibling
        if sibling3.name != "div":
            execution_text.append(sibling3.text)
        
        execution_text = list(filter(None, execution_text))
        return "\n".join(execution_text)

    def video_links(self):
        urls = []
        iframes = soup.select("iframe[gesture='media']")
        for item in iframes:
            urls.append(item.get('src'))
        return urls

