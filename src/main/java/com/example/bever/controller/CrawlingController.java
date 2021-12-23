package com.example.bever.controller;

import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
            Document google1 = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do").get();
            Document google2 = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do").post();

            response = Jsoup.connect("https://www.starbucks.co.kr/menu/drink_list.do")
                    .method(Connection.Method.GET)
                    .execute();
            Document google3 = response.parse();

            html=google3.html();

            Element btnK = google3.select("div[class=product_list]").first();

            System.out.println(btnK);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
