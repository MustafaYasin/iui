# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

from scrapy.exporters import CsvItemExporter
from pymongo import MongoClient

from ubungen.utils.contentpath import export_order
from transformers import pipeline
from transformers import FSMTForConditionalGeneration, FSMTTokenizer


class MongoPipeline(object):
    def open_spider(self, spider):
        self.connection = MongoClient("mongodb+srv://mustafayasin:nisani2404@cluster0.oxj2y.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
        self.db = self.connection["iui"]
        self.collection = self.db["ubungen"]
        self.collection.delete_many({})

    def process_item(self, item, spider):
        self.collection.insert_one(
            item
        )
        return item

    def close_spider(self, spider):
        self.connection.close()


class TranSummarisePipeline(object):
    def translate(self, msg):
        input_ids = self.tokenizer.encode(msg, return_tensors="pt")
        outputs = self.model.generate(input_ids)
        decoded = self.tokenizer.decode(outputs[0], skip_special_tokens=True)
        return decoded

    def summarize(self, article):
        summary = self.summarizer(article, max_length=90, min_length=20, do_sample=False)
        return summary

    def open_spider(self, spider):
        mname = "facebook/wmt19-de-en"
        self.summarizer = pipeline("summarization")
        self.tokenizer = FSMTTokenizer.from_pretrained(mname)
        self.model = FSMTForConditionalGeneration.from_pretrained(mname)

    def process_item(self, item, spider):
        item_new = {
            'muscle_group': self.translate(item['muscle_group']),
            'subset_muscles': self.translate(item['subset_muscles']),
            'exercise_title': self.translate(item['exercise_title']),
            'muscle_description_title': self.translate(item['muscle_description_title']),
            'muscle_description': self.summarize(self.translate(item['muscle_description']))[0]['summary_text'],
            'exercise_execution_title': self.translate(item['exercise_execution_title']),
            'exercise_execution': self.summarize(self.translate(item['exercise_execution']))[0]['summary_text']
        }
        return item_new

    def close_spider(self, spider):
        pass
