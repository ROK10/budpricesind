package com.naukma.budpricesind.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MaterialType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "type")
    private List<Material> materials;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}