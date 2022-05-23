package com.naukma.budpricesind.repository;

import com.naukma.budpricesind.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsRepository extends JpaRepository<Material,Long> {
}
