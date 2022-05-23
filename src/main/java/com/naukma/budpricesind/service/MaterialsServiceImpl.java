package com.naukma.budpricesind.service;

import com.naukma.budpricesind.model.Material;
import com.naukma.budpricesind.repository.MaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialsServiceImpl implements MaterialsService{

    @Autowired
    MaterialsRepository materialsRepository;

    @Override
    public void save(Material material) {
        materialsRepository.save(material);
    }

    @Override
    public boolean isExist(String name) {
        List<Material> materials = materialsRepository.findAll();
        for (Material n: materials) {
            if (n.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialsRepository.findAll();
    }
}
