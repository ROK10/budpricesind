package com.naukma.budpricesind.service;

import com.naukma.budpricesind.model.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialsService {

    void save(Material material);
    boolean isExist(String name);
    List<Material> getAllMaterials();
    void clear();
}
