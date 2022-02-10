import sumy
import nltk
from sumy.parsers.plaintext import PlaintextParser
from sumy.nlp.tokenizers import Tokenizer
from sumy.summarizers.lex_rank import LexRankSummarizer
from sumy.summarizers.luhn import LuhnSummarizer
from sumy.summarizers.kl import KLSummarizer


nltk.download('punkt')


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

parser = PlaintextParser.from_string(article, Tokenizer("english"))


summarizer = LexRankSummarizer()
summary = summarizer(parser.document, 3)
print(summary)


summarizer2 = LuhnSummarizer()
summary2 = summarizer2(parser.document, 3)
print(summary2)


from sumy.summarizers.lsa import LsaSummarizer
summarizer_lsa = LsaSummarizer()
summary_lsa = summarizer_lsa(parser.document, 2)
print(summary_lsa)



summarizer_kl = LsaSummarizer()
summary_kl = summarizer_kl(parser.document, 2)
print(summary_kl)
