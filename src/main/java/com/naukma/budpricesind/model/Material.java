package com.naukma.budpricesind.model;


import lombok.*;

import javax.persistence.*;


@Data
@Entity
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



}
