package com.naukma.budpricesind.api;

import com.naukma.budpricesind.model.Materials;
import com.naukma.budpricesind.service.MaterialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaterialsController {

    @Autowired
    MaterialsService materialsService;

    @GetMapping(value = "/")
    public List<Materials> getAllMaterials(){
        return  materialsService.getAllMaterials();
    }
}
