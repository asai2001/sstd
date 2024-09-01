package com.stuntingdetection.stuntingdetection.Entity;


import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "balita")
@Data
public class Balita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usia;  // Misalnya "Muda" atau "Tua"
    private double beratBadan;  // dalam kg
    private double tinggiBadan;  // dalam cm

}



