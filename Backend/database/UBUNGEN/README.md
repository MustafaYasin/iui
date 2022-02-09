
# ImdbScraper

This is a scrapy project to scrap movies' informations from www.imdb.com and store them into a csv file.  

### Installations

Before running this project, user needs to make sure [scrapy](https://scrapy.org/) is installed. It can be installed with `pip` like below:

```
pip install Scrapy
```

### Usage

First, clone the project and go to the project directory. 

```
git clone https://github.com/MustafaYasin/ubungen.git
cd ubungen
```

And run the crawler with:

```
scrapy crawl ubungen
```

This will fetch all movies's information which has minimum of 1000 votes on IMDB. You can however change this value passing an additional parameter. Like below: 

```
scrapy crawl ubungen -a min_votes=<your_preferred_no> 
```

### Licence 

This project is distributed under the terms of the [MIT license](LICENSE).
