from bs4 import BeautifulSoup
import requests

url = 'https://www.tripadvisor.co.uk/Attractions-g186338-Activities-London_England.html'
wd_data = requests.get(url)
soup = BeautifulSoup(wd_data.text,"lxml")
titles = soup.select(".attractions-attraction-overview-main-TopPOIs__name--GndbY")
imgs = soup.select("div.attractions-attraction-overview-main-TopPOIs__picture--3px_U > span > a > div > picture > div > img")
for title,img in zip(titles,imgs):
    data = {
        'title':title.get_text(),
        'img':img.get('data-url'),

        }
    print(data)
