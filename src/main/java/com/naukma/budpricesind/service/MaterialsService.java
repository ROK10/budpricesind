package com.naukma.budpricesind.service;

import com.naukma.budpricesind.model.Materials;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialsService {

    public void save(Materials materials);
    public boolean isExist(String name);
    public List<Materials> getAllMaterials();
}
