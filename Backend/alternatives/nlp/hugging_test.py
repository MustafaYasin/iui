from transformers import pipeline
from transformers import FSMTForConditionalGeneration, FSMTTokenizer
from transformers import T5Tokenizer, T5ForConditionalGeneration



summarizer = pipeline("summarization")

doc_muscle_description = """The shoulder muscles surround the shoulder joint. In bodybuilding, a distinction is usually made between three parts of the shoulder: the anterior shoulder, the lateral shoulder 
and the posterior shoulder. The shoulder muscles move and stabilize the shoulders not only in one direction, but in all directions. If you want to train your shoulder optimally, 
you should include exercises for all three parts of the shoulder in your training plan. Most basic upper body exercises train at least one part of the shoulder; however, the lateral 
shoulder in particular can also be trained through isolation exercises."""

doc_exercise_description = """The pre-bent reverse fly is a rather less frequently observed exercise on the training floor. Although the exercise is excellent for the posterior shoulder and neck, we would like to point out the no less effective alternatives. Especially widespread are the reverse flys on the cable pulley and the reverse flys on the butterfly machine. Below this article you will find more variations with a picture.
The Reverse Flys are mainly aimed at strengthening the posterior part of the deltoid muscle (musculus deltoideus pars spinalis) and the middle part of the hood muscle (musculus trapezius pars transversa)."""

doc_execution_description = """Hold a dumbbell in each hand and stand about shoulder width apart. Bend your knees slightly and bend your upright upper body forward until it is parallel to the floor. Make sure that your entire back remains extended and especially that your lower back forms a slight hollow cross position. This is highly advisable to avoid back and disc injuries. Your arms with the dumbbells are down and almost fully extended.

Now exhale and simultaneously move the dumbbells upward with your arms almost extended on both sides of your body. When you reach the end point of this movement phase at head height, you are training almost exclusively the back shoulder. If you bring the shoulder blades together at the end of the movement by bringing the arms up as far as the anatomy allows, the hood muscle will be used more.

Then inhale again and simultaneously bring both dumbbells down slowly and in a controlled manner to the starting position."""

article = doc_muscle_description

summary = summarizer(article, max_length=90, min_length=20, do_sample=False)

print(summary)



mname = "facebook/wmt19-de-en"
tokenizer = FSMTTokenizer.from_pretrained(mname)
model = FSMTForConditionalGeneration.from_pretrained(mname)

input_ids = tokenizer.encode(article, return_tensors="pt")
outputs = model.generate(input_ids)
decoded = tokenizer.decode(outputs[0], skip_special_tokens=True)
print(decoded) # Machine learning is great, isn't it?


summary_en_1 = summarizer(decoded, max_length=50, min_length=10, do_sample=False)

print(summary_en_1)

summary_en_2 = summarizer(article, max_length=50, min_length=10, do_sample=False)

print(summary_en_2)


tokenizer = T5Tokenizer.from_pretrained("t5-large")
model = T5ForConditionalGeneration.from_pretrained("t5-large")


# generate summary
input_ids = tokenizer.encode(article, return_tensors='pt')
summary_ids = model.generate(input_ids,
            min_length=50,
            max_length=80,
            num_beams=10,
            repetition_penalty=2.5,
            length_penalty=1.0,
            early_stopping=True,
            no_repeat_ngram_size=2,
            use_cache=True,
            do_sample = True,
            temperature = 0.8,
            top_k = 50,
            top_p = 0.95)

summary_text = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
print(summary_text)

