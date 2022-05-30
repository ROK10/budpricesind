package com.naukma.budpricesind.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class MaterialType {

    @Id
    @Column(name = "id", nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "text")
    private String text;

    @Column(name = "section")
    private String section;

    @OneToMany(mappedBy = "type")
    private List<Material> materials;
}