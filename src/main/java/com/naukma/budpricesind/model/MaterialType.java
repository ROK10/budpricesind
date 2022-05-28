package com.naukma.budpricesind.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "types")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialType {

    @Column(name = "typeName")
    private String typeName;

    @Column(name = "text")
    private String text;
}