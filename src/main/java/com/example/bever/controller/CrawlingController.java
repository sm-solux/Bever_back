package com.example.bever.controller;

import com.example.bever.domain.DrinkOwners;
import com.example.bever.domain.Drinks;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.service.CrawlingService;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CrawlingController {

    private final CrawlingService crawlingService;
    private final DrinksRepository drinksRepository;

    @ResponseBody
    @PostMapping("v1/crawling")
    public void crawl_and_put(){
        Map<String, Map<String,List<String>>> menulists = crawlingService.do_crawl();

        Map<String,List<String>> STARBUCKS_menulist = menulists.get("STARBUCKS");
        Map<String,List<String>> TWOSOME_menulist = menulists.get("TWOSOME");

        for(int i = 0; i<STARBUCKS_menulist.get("menu").size(); i++) {
            String menu_name = STARBUCKS_menulist.get("menu").get(i);
            String menu_img = STARBUCKS_menulist.get("img").get(i);
            if(drinksRepository.findAllByDrinkNameAndDrinkOwners(menu_name,DrinkOwners.STARBUCKS).size()==0) {
                Drinks drinks = Drinks.builder()
                        .drinkImageLink(menu_img)
                        .drinkName(menu_name)
                        .drinkOwners(DrinkOwners.STARBUCKS)
                        .build();
                drinksRepository.save(drinks);
            }
        }

        for(int i = 0; i<TWOSOME_menulist.get("menu").size(); i++) {
            String menu_name = TWOSOME_menulist.get("menu").get(i);
            String menu_img = TWOSOME_menulist.get("img").get(i);
            if(drinksRepository.findAllByDrinkNameAndDrinkOwners(menu_name,DrinkOwners.TWOSOME).size()==0) {
                Drinks drinks = Drinks.builder()
                        .drinkImageLink(menu_img)
                        .drinkName(menu_name)
                        .drinkOwners(DrinkOwners.TWOSOME)
                        .build();
                drinksRepository.save(drinks);
            }
        }

    }

}
