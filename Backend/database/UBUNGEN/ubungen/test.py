from transformers import pipeline
from transformers import FSMTForConditionalGeneration, FSMTTokenizer
mname = "facebook/wmt19-de-en"
summarizer = pipeline("summarization")
tokenizer = FSMTTokenizer.from_pretrained(mname)
model = FSMTForConditionalGeneration.from_pretrained(mname)
def translate(msg):
    input_ids = tokenizer.encode(msg, return_tensors="pt")
    outputs = model.generate(input_ids)
    decoded = tokenizer.decode(outputs[0], skip_special_tokens=True)
    return decoded
print(translate('Schultern'))