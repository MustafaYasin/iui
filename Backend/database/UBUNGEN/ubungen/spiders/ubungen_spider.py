import re
import scrapy
import pprint
from scrapy.loader import ItemLoader
from ubungen.items import UbungenItem
#from ubungen.utils.contentpath import name_to_xpath_mapper


class UbungenSpider(scrapy.Spider):
    name = "ubungen"
    start_urls = ["https://www.uebungen.ws/"]

    def __init__(self,  *args, **kwargs):
        super(UbungenSpider, self).__init__(*args, **kwargs)

    def parse(self, response):
        for muscle in response.xpath('//a[@class="auswahlboxlink"]/@href').extract():
            yield scrapy.Request(muscle, callback=self.parse_auswahlbox)

    def parse_auswahlbox(self, response):
        muscle_group = response.xpath('//span[@class="bc-text"]/text()').extract_first()
        for katauswahlbox in response.xpath('//a[@class="katauswahlboxlink"]/@href').extract():
            yield scrapy.Request(katauswahlbox, callback=self.parse_list_of_exercises, cb_kwargs=dict(
                muscle_group=muscle_group)
            )

    def parse_list_of_exercises(self, response, muscle_group):
        muscle_subset = response.xpath('//h1[@class="page-title"]/text()').extract_first()
        for exercise in response.xpath('//h3[@class="content-list-title"]/a/@href').extract():
            yield scrapy.Request(exercise, callback=self.call_exercises, cb_kwargs=dict(muscle_group=muscle_group,
                                                                                        muscle_subset=muscle_subset))

    def call_exercises(self, response, muscle_group, muscle_subset):
        TAG_RE = re.compile(r'<[^>]+>')
        description_in_german = TAG_RE.sub('', response.xpath('//div[@class="entry-content clearfix"]/p').extract_first())
        candidate_paragraphs = response.xpath('//div[@class="uebun-adsense-desktop-in-article"]/following-sibling::p').extract()

        filtered_paragraphs = []
        for paragraph in candidate_paragraphs:
            if "https://www.youtube.com" in paragraph:
                break
            else:
                filtered_paragraphs.append(TAG_RE.sub('', paragraph))
        exercise_execution = " ".join(filtered_paragraphs)
        exercise_execution = exercise_execution.replace("\n", " ")

        name_to_xpath_mapper = {
            'muscle_group': muscle_group,
            'subset_muscles': muscle_subset,
            'exercise_title': response.xpath('//h1[@class="entry-title"]/text()').extract_first(),
            'muscle_description_title': response.xpath('//div[@class="entry-content clearfix"]/h2/text()').extract_first(),
            'muscle_description': description_in_german,
            'exercise_execution_title': response.xpath('//div[@class="entry-content clearfix"]/h2/text()').extract()[1],
            'exercise_execution': exercise_execution
        }
        yield name_to_xpath_mapper
