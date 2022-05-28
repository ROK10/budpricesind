package com.naukma.budpricesind.job;

import com.naukma.budpricesind.model.Material;
import com.naukma.budpricesind.service.MaterialsService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ParseTask {

    public String country = "";
    public String uAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";

    public void parseNewMaterial(MaterialsService materialsService, String materialName, String countryName){
        country = countryName;
        String url = "https://flagma." + countryName + "/products/q=" + materialName + "/price:wholesale/";
        //String url = "https://flagma.ua/products/bitum/q=дорожный+битум/price:wholesale/";
        parseAllPages(url, findLastPage(url),materialsService);
    }

    public void parseAllPages(String url, int numberOfPages,MaterialsService materialsService){
        for (int i = 1; i <= numberOfPages; i++){
            url += "page-" + i + "/";
            try {
                Document doc = Jsoup.connect(url)
                        .userAgent(uAgent)
                        .timeout(50000)
                        .referrer("https://www.google.com/")
                        .get();
                parsePage(doc,materialsService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int findLastPage(String url) {
        int lastPage = 1;
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent(uAgent)
                    .timeout(10000)
                    .referrer("https://www.google.com/")
                    .get();
            Elements pageNumber;
            if (country.equals("pl")){
                pageNumber = doc.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[3]/ul/li[6]/span");
            }else {
                if (country.equals("kz")){
                    pageNumber = doc.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[2]/ul/li[7]/span");
                }else {
                    pageNumber = doc.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[3]/ul/li[7]/span");
                }
            }
            Element el = pageNumber.first();
            if (el == null){
                return 2;
            }else {
                lastPage = Integer.parseInt(el.ownText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lastPage >3)
            return 3;
        return lastPage;
    }

    public void parsePage(Document document, MaterialsService materialsService){
        for (int i = 0; i <= 200; i++){
            Elements materialsNames;
            Elements materialsPrices;
            Elements materialsUnits;
            Elements materialsProviders;
            if (country.equals("kz")||country.equals("pl")){
                materialsNames = document.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[1]/div/a");
                materialsPrices = document.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]/span");
                materialsUnits = document.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]");
                materialsProviders = document.selectXpath("/html/body/div[1]/main/div[8]/div[2]/div[1]/div[1]/div[" + i + "]/div[2]/div[1]/span");
            }else {
                materialsNames = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[1]/div/a");
                materialsPrices = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]/span");
                materialsUnits = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[1]/div[2]");
                materialsProviders = document.selectXpath("/html/body/div[1]/main/div[9]/div[2]/div[1]/div[1]/div[" + i + "]/div[2]/div[1]/span");
            }
            for (Element el: materialsNames){
                Material obj = new Material();
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
                    for (Element el3: materialsProviders) {
                        String provider = el3.ownText();
                        obj.setProvider(provider);
                    }
                    materialsService.save(obj);
                }
            }
        }
    }
}
