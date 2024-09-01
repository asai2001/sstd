package com.stuntingdetection.stuntingdetection.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "fuzzy_rule")
public class FuzzyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usia;  // Muda, Tua
    private String beratBadanCondition;  // Kurang, Normal
    private double beratBadanMin;  // Batas bawah Berat Badan
    private double beratBadanMax;  // Batas atas Berat Badan
    private String tinggiBadanCondition;  // Pendek, Normal
    private double tinggiBadanMin;  // Batas bawah Tinggi Badan
    private double tinggiBadanMax;  // Batas atas Tinggi Badan
    private String output;  // Stunting atau Normal
}
