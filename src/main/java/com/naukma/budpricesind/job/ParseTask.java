package com.naukma.budpricesind.job;

import com.naukma.budpricesind.model.Materials;
import com.naukma.budpricesind.service.MaterialsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.SystemOut;

@Component
public class ParseTask {

    @Autowired
    MaterialsService materialsService;


    @Scheduled(fixedDelay = 10000)
    public void parseNewMaterial(){
        String url = "https://flagma.ua/products/bitum/q=дорожный+битум/price:wholesale/";
        parseAllPages(url, findLastPage(url));

        ///html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/span
        ///html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/text()

        ///html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[10]/div[1]/div[2]/span
        ///html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[10]/div[1]/div[2]/text()

    }

    public void parseAllPages(String url, int numberOfPages){
        for (int i = 1; i <= numberOfPages; i++){
            url += "page-" + i + "/";
            try {
                Document doc = Jsoup.connect(url)
                        .userAgent("Chrome")
                        .timeout(10000)
                        .referrer("https://www.google.com/")
                        .get();
                parsePage(doc);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int findLastPage(String url) {
        int lastPage = 0;
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Chrome")
                    .timeout(10000)
                    .referrer("https://www.google.com/")
                    .get();
            Elements pageNumber = doc.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[3]/ul/li[7]/span");
            Element el = pageNumber.first();
            lastPage = Integer.parseInt(el.ownText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastPage;
    }

    public void parsePage(Document document){
        for (int i = 0; i <= 200; i++){
            Elements materialsNames = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[1]/div/a");
            Elements materialsPrices = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]/span");
            Elements materialsUnits = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]");
            for (Element el: materialsNames){
                Materials obj = new Materials();
                String name = el.ownText();
                if(!materialsService.isExist(name)){
                    obj.setName(name);
                    for (Element el1: materialsPrices) {
                        String price = el1.ownText();
                        if(el1.ownText().contains("-")||el1.ownText().isEmpty()){
                            price = el1.child(0).ownText();
                        }
                        obj.setPrice(Double.parseDouble(price.replace(" ", "")));
                    }
                    for (Element el2: materialsUnits) {
                        String unit = el2.ownText();
                        obj.setUnit(unit);
                    }
                    materialsService.save(obj);
                }
            }
        }
    }
}
