import sys
from Backend.objects.exercise import Exercise
from Backend.objects.muscle_group import MuscleGroup
from Backend.objects.scrap_website import ScrapWebsite



main_muscles = ["schultern", "ruecken", "brust", "arme", "bauch", "po", "beine"]

# replace with variables fromdatabase database?
sub_muscles_url = []
list_of_exercises_url = []


# need string replacements for url
def replace_string(replace_string):
    test = replace_string.replace(" / ", "-").replace(" ", "-").lower()
    return test.replace("ü", "ue").replace("ä", "ae").replace("ö", "oe").replace("ß", "ss")


def add_url_muscles(new_muscle):
    for s_muscle in new_muscle:
        sub_muscles_url.append(replace_string(muscle_content.muscle_title) + "/" + replace_string(s_muscle))


def add_url_exercises(new_exercises):
    for exercise in new_exercises:
        exercise_str_replace = replace_string(exercise)
        if exercise_str_replace not in list_of_exercises_url:
            list_of_exercises_url.append(exercise_str_replace)


for muscle in main_muscles:
    ScrapWebsite.call_website(muscle)
    muscle_content = MuscleGroup(ScrapWebsite.muscle_title(), ScrapWebsite.muscle_description(),
                                 ScrapWebsite.subset_muscles(), ScrapWebsite.list_of_exercises())

    # Save required urls from sub muscles and exercises in array(delete/change for-loops if variables come from databasedatabase?)
    add_url_muscles(muscle_content.subset_muscles)
    add_url_exercises(muscle_content.all_exercises)

    # ----> hier Objekt übersetzen und zusammenfassen + in Muscle DB speichern

for s_muscle in sub_muscles_url:
    ScrapWebsite.call_website(s_muscle)
    muscle_content = MuscleGroup(ScrapWebsite.muscle_title(), ScrapWebsite.muscle_description(), "",
                                 ScrapWebsite.list_of_sub_exercises())

    # Save required urls from sub muscles and exercises in array(delete/change for-loops if variables come from databasedatabase?)
    add_url_exercises(muscle_content.all_exercises)

    # ----> hier Objekt übersetzen und zusammenfassen + in Muscle DB speichern


def clean_urls(clean_string):
    if clean_string == "pull-over-ueberzuege-mit-der-langhantel":
        return "pull-over-ueberzuge-mit-der-langhantel"
    elif clean_string == "sit-ups-auf-der-negativbank":
        return "sit-ups-auf-der-negativban"
    elif clean_string == "stehendes-wadenheben-mit-dem-theraband":
        return "stehendes-wadenheben-mit-dem-teraband"
    else:
        return clean_string


# methods for exersice_description, video_links, exersice_exection does not work yet
for ex in list_of_exercises_url:
    ex = clean_urls(ex)
    ScrapWebsite.call_website(ex)
    exercise_content = Exercise("", ScrapWebsite.exercise_title(), ScrapWebsite.exercise_description(),
                                ScrapWebsite.exercise_execution(), ScrapWebsite.equipment(),
                                ScrapWebsite.target_muscles(), ScrapWebsite.exercise_level(),
                                ScrapWebsite.synonym_titles(), ScrapWebsite.supporting_muscles(),
                                ScrapWebsite.muscle_image(), ScrapWebsite.video_links())
    # print(exercise_content.exercise_description)
