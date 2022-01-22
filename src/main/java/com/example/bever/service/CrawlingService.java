package com.example.bever.service;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CrawlingService {

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static final String WEB_DRIVER_PATH = "src/main/resources/chromedriver";

    private static final String STARBUCKS_URL = "https://www.starbucks.co.kr/menu/drink_list.do";
    private static final String TWOSOME_URL = "https://mo.twosome.co.kr/mn/menuInfoList.do?grtCd=1&pMidCd=0";

    public void do_crawl(){
        WebDriver driver = setUp();

//        List<String> STARBUCKS_menulist = crawl_STARBUCKS(driver);


        List<String> TWOSOME_menulist = crawl_TWOSOME(driver);


        driver.close();
        driver.quit();

    }

    private WebDriver setUp(){

        //해당 브라우저에 다양한 옵션을 주기위해 ChromeOptions 객체화
        ChromeOptions options = new ChromeOptions();
        //옵션 설정
        //headless : 브라우저 실행이 내부로 전환된다, 눈에 안보인다.
        options.addArguments("headless");

        //운영체제에 드라이버 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        //설정한 옵션 객체를 ChromeDriver 생성자를 통해 전달한다.
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    private List<String> crawl_STARBUCKS(WebDriver driver){
        //요청할 URL을 get()에 전달하면 응답된 페이지를 브라우저를 통해 확인할 수 있다.


        driver.get(STARBUCKS_URL);
        try {
            //브라우저가 실행되는 시간을 기다려준다.
            Thread.sleep(1000);
        }catch (InterruptedException e){
            // 자바가 셀레니움보다 빨라서 1초씩은 기다려줍니다. 브라우저 열리기도 전에 태그를 가져올수 있기떄문에
            e.printStackTrace();
        }

        // btn-more-fontbold : 더 보기 버튼 클래스 명
        //해당 URL에서 더 보기 버튼을 객체로 가져온다

        List<String> menu_list = new LinkedList<>();

        for(WebElement el:driver.findElements(By.className("menuDataSet"))){
            menu_list.add(el.findElement(By.cssSelector("dd")).getText());
        }
        return menu_list;
    }

    private List<String> crawl_TWOSOME(WebDriver driver){

        List<String> menu_list = new LinkedList<>();

        for( int i = 1 ; i < 4 ; i++ ) {
            // 요청할 URL을 get()에 전달하면 응답된 페이지를 브라우저를 통해 확인할 수 있다.

            // 투썸플레이스는 메뉴 페이지가 에스프레소, 티, 음료 세가지 카테고리로 나누어져있으며
            // 각 페이지는 공통주소 맨 뒤에 숫자가 1,2,3으로 덧붙여진 주소를 갖는다.
            String url =TWOSOME_URL+i;

            driver.get(url);
            try {
                //브라우저가 실행되는 시간을 기다려준다.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 자바가 셀레니움보다 빨라서 1초씩은 기다려줍니다. 브라우저 열리기도 전에 태그를 가져올수 있기떄문에
                e.printStackTrace();
            }


            //<p class="menu-title">아메리카노</p>와 같이 p 태그안의 텍스트로 된 메뉴명을 가져온다.
            for (WebElement el : driver.findElements(By.className("menu-title"))) {
                menu_list.add(el.getText());
            }
        }

        return menu_list;

    }
}
