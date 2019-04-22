import os
from bs4 import BeautifulSoup
import requests
url = 'https://www.tripadvisor.co.uk/Attraction_Review-g186338-d187555-Reviews-The_British_Museum-London_England.html'

def getCityLinks(theurl):
    wd_data = requests.get(theurl)
    soup = BeautifulSoup(wd_data.text, "lxml")
    links=soup.select('div.ap_navigator > a[class="taLnk"]')
    lst = []
    for link in links:
        data= {
            'title': link.get_text(),
            'src': "https://www.tripadvisor.co.uk"+str(link.get('href')),
        }
        if(not data is None):
            lst.append(data)
    lst = lst[2:]
    lst = lst[:-1]
    #print(type(data))
    return lst


def attractionABOUT(theurl):
    wd_data = requests.get(theurl)
    soup = BeautifulSoup(wd_data.text, "lxml")
    titles = soup.select('.attractions-attraction-detail-about-card-AttractionDetailAboutCard__section--1_Efg')
    rates=soup.select('.overallRating')
    content = ''
    if len(rates):
        content = ('Rates:'+rates[0].string)
    for title in titles:
        data = title.get_text()
        content = content + '\n' + str(data)
    content = content + '\n'
    return content

def getAttraction1(url):
    wd_data = requests.get(url)
    soup = BeautifulSoup(wd_data.text, "lxml")
    links = soup.select('.attractions-attraction-overview-main-TopPOIs__name--GndbY')
    lst = []
    for link in links:
        data = {
        'title': link.get_text(),
        'src': "https://www.tripadvisor.co.uk"+str(link.get('href'))
        }
        if(not data is None):
            lst.append(data)
        #print(data)
    #print(lst)
    return lst

def getAttraction2(url):
    wd_data = requests.get(url)
    soup = BeautifulSoup(wd_data.text, "lxml")
    links = soup.select('div.listing_title  > a')
    lst = []
    for link in links:
        data = {
        'title': link.get_text(),
        'src': "https://www.tripadvisor.co.uk"+str(link.get('href'))
        }
        if(not data is None):
            lst.append(data)
        #print(data)
    #print(lst)
    return lst

'''def img2(url):
    wd_data = requests.get(url)
    soup = BeautifulSoup(wd_data.text, "lxml")
    links = soup.select('div.attraction_clarity_cell  > div > div > div.photo_booking > a')
    print(links)
    lst1 = []
    for link in links:
        data = link.get('href')
        #print(data)
        lst1.append("https://www.tripadvisor.co.uk"+str(data))
    links = soup.select('div.attraction_clarity_cell  > div > div > div.photo_booking > a')
    lst2 = []
    for link in links:
        data = link.get('src')
        print(data)
        lst2.append(str(data))
    return lst
    '''
def attractionImg(url):
    wd_data = requests.get(url)
    soup = BeautifulSoup(wd_data.text, "lxml")
    links = soup.select('div.prw_rup.prw_common_basic_image.xlarge.landscape > div > img')
    if len(links):
        link = links[0]
        data = link.get('src')
        return data
    return None
#attractionABOUT(url)
#img2('https://www.tripadvisor.co.uk/Attractions-g187069-Activities-Manchester_Greater_Manchester_England.html')
#attractionImg('https://www.tripadvisor.co.uk/Attraction_Review-g187069-d212251-Reviews-Science_and_Industry_Museum-Manchester_Greater_Manchester_England.html')


'''def files(name,text):              #定义函数名
    b = os.getcwd()[:-4] + 'new\\'

    if not os.path.exists(b):     #判断当前路径是否存在，没有则创建new文件夹
        os.makedirs(b)

	xxoo = b + name + '.txt'    #在当前py文件所在路径下的new文件中创建txt

    file = open(xxoo,'w')

    file.write(text)        #写入内容信息

    file.close()
    print ('ok')
txt('test','hello,python')       #创建名称为test的txt文件，内容为hello,pytho
'''

def CreateFolder(name):
    folder = os.path.abspath('.')+'/'+str(name)
    if not os.path.exists(folder):
        os.makedirs(folder)

def CreateFile(FolderName, AttractionName, content):
    folder = os.path.abspath('.')+'/'+str(FolderName)
    attraction = folder + '/' +str(AttractionName) +'.txt'
    file = open(attraction,'w')
    file.write(content)
    file.close()

cities = getCityLinks('https://www.tripadvisor.co.uk/Attractions-g186217-Activities-England.html')
for city in cities:
    cityName = city['title']
    #print(cityName)
    CreateFolder(cityName)
    cityLink = city['src']
    attractions1 = getAttraction1(cityLink)
    attractions2 = getAttraction2(cityLink)
    #attractions2 = getAttraction2(cityLink)
    #print(attractions2)
    for attraction in attractions1:
        attractionName = attraction['title']
        attractionName = attractionName.replace("/", " ")
        print(attractionName)
        attractionLink = attraction['src']
        about = attractionABOUT(attractionLink)
        print
        img = attractionImg(attractionLink)
        content = str(about) + '\n' + str(img)
        CreateFile(cityName, attractionName, content)
    for attraction in attractions2:
        attractionName = attraction['title']
        attractionName = attractionName.replace("/", " ")
        print(attractionName)
        attractionLink = attraction['src']
        about = attractionABOUT(attractionLink)
        print
        img = attractionImg(attractionLink)
        content = str(about) + '\n' + str(img)
        CreateFile(cityName, attractionName, content)
