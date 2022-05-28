package com.naukma.budpricesind.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country {

    @Column(name = "countryName")
    private String countryName;

    @Column(name = "text")
    private String text;
}
