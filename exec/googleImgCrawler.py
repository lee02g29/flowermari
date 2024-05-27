import ssl
ssl._create_default_https_context = ssl._create_unverified_context

from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
import time
import urllib.request

from selenium.webdriver.chrome.service import Service as ChromeService
from webdriver_manager.chrome import ChromeDriverManager

import os

# searchKey = input('검색 키워드 입력:')

# flowerName = input("꽃 이름 입력: ")

# # searchKeys = [flowerName, f"{flowerName} flower", f"bouquet of {flowerName}", f"flowershop {flowerName}"]
# searchKeys = [f"bouquet of {flowerName}"]

searchKeys = [f"a bouquet of flowers"]

if not os.path.exists("./imgs"):
    os.makedirs("./imgs")


for searchKey in searchKeys:
    options = webdriver.ChromeOptions()
    options.add_argument("headless")
    #driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()), chrome_options= options)
    driver = webdriver.Chrome(service=ChromeService(ChromeDriverManager().install()), chrome_options= options)

    time.sleep(2)

    driver.get("https://www.google.co.kr/imghp?hl=ko&tab=wi&authuser=0&ogbl")

    time.sleep(2)

    elem = driver.find_element("name", "q")
    elem.send_keys(searchKey)
    elem.send_keys(Keys.RETURN)

    time.sleep(2)

    # Get scroll height
    last_height = driver.execute_script("return document.body.scrollHeight")
    while True:
        # Scroll down to bottom
        driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
        # Wait to load page
        time.sleep(2)
        # Calculate new scroll height and compare with last scroll height
        new_height = driver.execute_script("return document.body.scrollHeight")
        if new_height == last_height:
            try:
                driver.find_element(By.XPATH, '//*[@id="islmp"]/div/div/div/div/div[1]/div[2]/div[2]/input').click()
            except:
                break
        last_height = new_height

    images = driver.find_elements(By.CSS_SELECTOR, ".rg_i")
    count = 1
    for image in images:
        try:
            image.click()

            time.sleep(2)
            
            imgUrl = driver.find_element(
                By.XPATH,
                '//*[@id="Sva75c"]/div[2]/div[2]/div[2]/div[2]/c-wiz/div/div/div/div/div[3]/div[1]/a/img[1]'
            ).get_attribute("src")
            
            urllib.request.urlretrieve(imgUrl, f'./imgs/{searchKey}{str(count)}.jpg')
            
            count = count + 1
        except Exception as e:
            # print('e : ', e)
            pass

    
    driver.quit()

    time.sleep(2)

    #driver.close()
