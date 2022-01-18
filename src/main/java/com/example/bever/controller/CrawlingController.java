package com.example.bever.controller;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CrawlingController {

    @ResponseBody
    @PostMapping("v1/crawling")
    public void crawling(){
        // Response로부터 Document 얻어오기
        Connection.Response response = null;
        String html = "";
        try {
            // 간략화된 GET, POST
//            Document google1 = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do").get();
//            Document google2 = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do").post();

            response = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do")
                    .method(Connection.Method.GET)
                    .execute();
            Document doc = response.parse();
            doc=Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do").post();

            html=doc.html();

             Elements t = doc.select("li.menuDataSet dd");

//            Elements t = doc.select("a").select(".goDrinkView");
//            Elements t = doc.select("a.goDrinkView");
            System.out.println(doc);

            System.out.println("====================");

            // WebDriver 경로 설정
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");


            ChromeOptions options = new ChromeOptions();

            //브라우저가 눈에 보이지 않고 내부적으로 돈다.
            //설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.
            options.addArguments("headless");

            options.addArguments("--start-maximized"); // 전체화면으로 실행
            options.addArguments("--disable-popup-blocking"); // 팝업 무시
            options.addArguments("--disable-default-apps"); // 기본앱 사용안함

            WebDriver driver = new ChromeDriver(options);
            JavascriptExecutor js = (JavascriptExecutor)driver;
            driver.get("https://www.starbucks.co.kr/menu/drink_list.do");
            driver.manage().window().fullscreen();
            driver.manage().timeouts().setScriptTimeout(20,TimeUnit.SECONDS);

            long start_time = System.currentTimeMillis();
            for(Element table : t) {
            }

//            Element btnK = google3.select("ul[class=product_cold_brew]").first();

        } catch (IOException e) {
            e.printStackTrace();
        }




//        System.out.println(js.executeScript(driver.getPageSource()));

//        System.out.println();
//        System.out.println(driver.getPageSource());

    }

}
