package com.naukma.budpricesind.model;


import lombok.*;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "materials")
public class Material {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "unit")
    private String unit;

    @Column(name = "price")
    private double price;

    @Column(name = "provider")
    private String provider;

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "material_types")
    private MaterialType type;

    public MaterialType getMaterialType() {
        return type;
    }

    public void setMaterialType(MaterialType materialType) {
        this.type = materialType;
    }
}
