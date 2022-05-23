package com.naukma.budpricesind.controller;

import com.naukma.budpricesind.service.MaterialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class MaterialsController {

    @Autowired
    MaterialsService materialsService;

    /*@GetMapping(value = "/")
    public List<Materials> getAllMaterials(){
        //return  materialsService.getAllMaterials();
        return  "materials-list.html";
    }*/
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String getMaterials(Model model){
        model.addAttribute("materials", materialsService.getAllMaterials());
        return  "materials-list";
    }
}

