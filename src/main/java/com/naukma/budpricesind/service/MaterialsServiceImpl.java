package com.naukma.budpricesind.service;

import com.naukma.budpricesind.model.Materials;
import com.naukma.budpricesind.repository.MaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialsServiceImpl implements MaterialsService{

    @Autowired
    MaterialsRepository materialsRepository;

    @Override
    public void save(Materials materials) {
        materialsRepository.save(materials);
    }

    @Override
    public boolean isExist(String name) {
        List<Materials> materials = materialsRepository.findAll();
        for (Materials n: materials) {
            if (n.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Materials> getAllMaterials() {
        return materialsRepository.findAll();
    }
}
