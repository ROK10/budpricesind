package com.naukma.budpricesind.service;

import com.naukma.budpricesind.model.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialsService {

    public void save(Material material);
    public boolean isExist(String name);
    public List<Material> getAllMaterials();
}
