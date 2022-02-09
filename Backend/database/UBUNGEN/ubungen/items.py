# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
import re

from scrapy.loader.processors import Join, MapCompose, TakeFirst, Compose
from w3lib.html import remove_tags
import sys

if sys.version_info[0] >= 3:
    unicode = str

class UbungenItem(scrapy.Item):
    release_year = scrapy.Field(output_processor=TakeFirst())
    rating = scrapy.Field(output_processor=TakeFirst())
    total_votes = scrapy.Field(
        input_processor = MapCompose(lambda x:x.replace(',','')),
        output_processor=TakeFirst()
    )
    director_name = scrapy.Field(
        output_processor = Join('|')
    )
    #writer_name = scrapy.Field() # not done
    top_actors = scrapy.Field(
        output_processor = Join('|')
    )
    metascore = scrapy.Field(output_processor=TakeFirst())
    #oscars_wins = scrapy.Field()
    #other_awards = scrapy.Field()
    #nominations = scrapy.Field()
    storyline = scrapy.Field(
        output_processor= Join(),
        input_processor = MapCompose(unicode.strip)
    )
    tagline = scrapy.Field(
        output_processor=TakeFirst(),
        input_processor = MapCompose(unicode.strip)
    )
    genres = scrapy.Field(
        output_processor = Join('|')
    )
    country = scrapy.Field(output_processor=TakeFirst())
    languages = scrapy.Field(
        output_processor = Join('|')
    )
    filming_locations = scrapy.Field(output_processor=Join('|'))
    budget = scrapy.Field(
        output_processor=TakeFirst(),
        input_processor = MapCompose(unicode.strip, lambda x: x.replace(',',''))
    )
    gross_income = scrapy.Field(
        output_processor=TakeFirst(),
        input_processor=MapCompose(unicode.strip, lambda x: x.replace(',',''))
    )
    color = scrapy.Field(output_processor=TakeFirst())
    production_company = scrapy.Field(
        output_processor=Join('|')
    )
    runtime = scrapy.Field(
        input_processor = MapCompose(lambda x: re.findall(r'\d+', x)[0]),
        output_processor=TakeFirst()
    )