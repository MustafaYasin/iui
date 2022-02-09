
name_to_xpath_mapper = {
    'movie_name': '//nav[@class="breadcrumb"]/a/text()',
    'muscle_group': '//nav[@class="breadcrumb"]/a/text()',
    'subset_muscles': '//nav[@class="breadcrumb"]/span/a/span/text()',
    'exercise_title': '//h1[@class="entry-title"]/text()',
    'muscle_description_title': '//div[@class="entry-content clearfix"]/h2/text()',
    'muscle_description': '//div[@class="entry-content clearfix"]/p',
    'exercise_execution_title': '//div[@class="entry-content clearfix"]/h2/text()',

}

export_order = ["muscle_group", "subset_muscles", "exercise_title", "muscle_description_title", "muscle_description",
                "exercise_execution_title"]
