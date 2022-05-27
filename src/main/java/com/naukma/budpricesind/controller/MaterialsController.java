package com.naukma.budpricesind.controller;

import com.naukma.budpricesind.job.ParseTask;
import com.naukma.budpricesind.model.MaterialType;
import com.naukma.budpricesind.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private MaterialType materialType= new MaterialType();
    private MaterialType materialType2= new MaterialType();
    List<MaterialType> materialsTypes = new ArrayList<>();


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getIndex(Model model
            ,@ModelAttribute("selectedMaterial") MaterialType selectedMaterial){
        materialType.setText("Бітум дорожній");
        materialType.setTypeName("Битум+дорожный");

        materialType2.setText("Цемент");
        materialType2.setTypeName("Цемент");
        materialsTypes.add(materialType);
        materialsTypes.add(materialType2);
        model.addAttribute("materialsTypes", materialsTypes);
        return  "index";
    }


    @RequestMapping(value = "/materials",method = RequestMethod.GET)
    public String getMaterials(Model model,
                               @ModelAttribute("selectedMaterial") MaterialType selectedMaterial){
        String s = selectedMaterial.getTypeName();
        model.addAttribute("materials", materialsService.findMaterials());
        return  "materials-list";
    }


}

