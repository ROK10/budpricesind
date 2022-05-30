package com.naukma.budpricesind.controller;

import com.naukma.budpricesind.task.ParseTask;
import com.naukma.budpricesind.model.Country;
import com.naukma.budpricesind.model.MaterialType;
import com.naukma.budpricesind.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MaterialsController {

    @Autowired
    MaterialsService materialsService;

    List<MaterialType> materialsTypes = new ArrayList<>();
    List<Country> countries = new ArrayList<>();

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getIndex(Model model,
                           @ModelAttribute("selectedMaterial") MaterialType selectedMaterial,
                           @ModelAttribute("selectedCountry") Country selectedCountry){
        if (!isAdded) {
            addTypes();
        }
        model.addAttribute("materialsTypes", materialsTypes);
        model.addAttribute("countries",countries);
        return  "index";
    }


    @RequestMapping(value = "/materials",method = RequestMethod.GET)
    public String getMaterials(Model model,
                               @ModelAttribute("selectedMaterial") MaterialType selectedMaterial,
                               @ModelAttribute("selectedCountry") Country selectedCountry,
                               @RequestParam(name = "page") int page){
        materialsService.clear();
        ParseTask task = new ParseTask();
        selectedMaterial.setSection(search(materialsTypes, selectedMaterial.getTypeName()).getSection());
        task.parseNewMaterial(materialsService, selectedMaterial, selectedCountry.getCountryName(), page);
        model.addAttribute("materials", materialsService.getAllMaterials());
        return  "materials-list";
    }

    public boolean isAdded = false;

    public void addTypes(){
        MaterialType type1 = new MaterialType();
        MaterialType type2 = new MaterialType();
        MaterialType type3 = new MaterialType();
        MaterialType type4 = new MaterialType();
        MaterialType type5 = new MaterialType();
        MaterialType type6 = new MaterialType();
        MaterialType type7 = new MaterialType();
        MaterialType type8 = new MaterialType();
        MaterialType type9 = new MaterialType();
        MaterialType type10 = new MaterialType();
        MaterialType type11 = new MaterialType();
        MaterialType type12 = new MaterialType();
        MaterialType type13 = new MaterialType();
        MaterialType type14 = new MaterialType();
        Country c1 = new Country();
        Country c2 = new Country();
        Country c3 = new Country();
        Country c4 = new Country();
        type1.setTypeName("Цемент");
        type1.setText("Цемент");
        type1.setSection("/cement");
        type2.setTypeName("Металопрокат");
        type2.setText("Металопрокат");
        type2.setSection("/metallicheskaya-armatura");
        type3.setTypeName("Асфальтобетонная+смесь");
        type3.setText("Асфальтобетонна суміш");
        type3.setSection("/asfalt-asfaltobeton");
        type4.setTypeName("Щебень+кубовидный");
        type4.setText("Щебінь кубовидний");
        type4.setSection("/shcheben-graviy");
        type5.setTypeName("Металлическое+барьерное+ограждение");
        type5.setText("Металеве бар'єрне огородження");
        type5.setSection("/dorozhnoe-ograzhdenie");
        type6.setTypeName("Краски+для+дорожной+разметки");
        type6.setText("Фарби для дорожньої розмітки");
        type6.setSection("/kraski-dlya-dorozhnoy-razmetki");
        type7.setTypeName("Дизельное+топливо");
        type7.setText("Паливо дизельне");
        type7.setSection("/dizelnoe-toplivo");
        type8.setTypeName("Полимерные+ленты+для+дорожной+разметки");
        type8.setText("Полімерні стрічки для дорожньої розмітки");
        type8.setSection("/konveyernye-lenty");
        type9.setTypeName("Звенья+водопропускных+труб");
        type9.setText("Ланки водопропускних труб");
        type9.setSection("/truby-zhelezobetonnye-asbestocementnye");
        type10.setTypeName("Дорожный+битум");
        type10.setText("Дорожній бітум");
        type10.setSection("/bitum");
        type11.setTypeName("битумная+эмульсия");
        type11.setText("Емульсія бітумна");
        type11.setSection("/bitum");
        type12.setTypeName("Щебень");
        type12.setText("Щебінь");
        type12.setSection("/shcheben-graviy");
        type13.setTypeName("Щебеночно+песчаная+смесь");
        type13.setText("Щебенево-піщана суміш");
        type13.setSection("/universalnye-stroitelnye-smesi");
        type14.setTypeName("Мазут+топливный");
        type14.setText("Мазут паливний");
        type14.setSection("/mazut");
        c1.setCountryName("ua");
        c1.setText("Україна");
        c2.setCountryName("by");
        c2.setText("Білорусь");
        c3.setCountryName("kz");
        c3.setText("Казахстан");
        c4.setCountryName("pl");
        c4.setText("Польща");
        materialsTypes.add(type1);
        materialsTypes.add(type2);
        materialsTypes.add(type3);
        materialsTypes.add(type4);
        materialsTypes.add(type5);
        materialsTypes.add(type6);
        materialsTypes.add(type7);
        materialsTypes.add(type8);
        materialsTypes.add(type9);
        materialsTypes.add(type10);
        materialsTypes.add(type11);
        materialsTypes.add(type12);
        materialsTypes.add(type13);
        materialsTypes.add(type14);
        countries.add(c1);
        countries.add(c2);
        countries.add(c3);
        countries.add(c4);
        isAdded=true;
    }

    public static MaterialType search(List <MaterialType> list, String typeName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTypeName().equals(typeName)){
                return list.get(i);
            }
        }
        return null;
    }
}

