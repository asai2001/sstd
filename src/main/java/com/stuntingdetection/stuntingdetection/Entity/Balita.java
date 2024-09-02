package com.stuntingdetection.stuntingdetection.Entity;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name = "balita")
@Data
public class Balita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nama;
    private String usia;
    private String jenisKelamin;
    private String keterangan;
    private String skorStunting;
    private double beratBadan;
    private double tinggiBadan;
//
//    private String beratBadanCondition; // Menggunakan string, misalnya "kurang", "normal"
//    private String tinggiBadanCondition; // Menggunakan string, misalnya "pendek", "normal"
//

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}



