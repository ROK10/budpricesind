package com.naukma.budpricesind.service;

import com.naukma.budpricesind.job.ParseTask;
import com.naukma.budpricesind.model.Material;
import com.naukma.budpricesind.repository.MaterialsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialsServiceImpl implements MaterialsService{

    private MaterialsRepository materialsRepository;

    @Override
    @Transactional
    public void save(Material material) {
        materialsRepository.save(material);
    }

    @Override
    public List<Material> findMaterials(String search){
        materialsRepository.findMateralByTypeNameContains(search);
    }

    @Override
    @Transactional
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
    @Transactional
    public List<Material> getAllMaterials() {
        return materialsRepository.findAll();
    }
}
