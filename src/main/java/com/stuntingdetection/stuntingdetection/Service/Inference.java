package com.stuntingdetection.stuntingdetection.Service;

import com.stuntingdetection.stuntingdetection.Entity.Balita;
import com.stuntingdetection.stuntingdetection.Entity.FuzzyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Inference {

    @Autowired
    private FuzzyRuleService fuzzyRuleService;

    public List<FuzzyRule> findRulesByConditions(String usia, String beratBadanCondition, String tinggiBadanCondition) {
        return fuzzyRuleService.findByUsiaAndBeratBadanConditionAndTinggiBadanCondition(
                usia, beratBadanCondition, tinggiBadanCondition);
    }

    public double calculateFinalOutput(Balita balita) {
        List<FuzzyRule> matchingRules = fuzzyRuleService.findMatchingRule(

                balita.getUsia(),
                balita.getBeratBadan(),
                balita.getTinggiBadan()
        );

        if (matchingRules.isEmpty()) {
            throw new IllegalArgumentException("Tidak ditemukan aturan yang sesuai.");
        }

        double numerator = 0.0; // Untuk menyimpan hasil dari Σ(αi * yi)
        double denominator = 0.0; // Untuk menyimpan hasil dari Σαi

        for (FuzzyRule rule : matchingRules) {
            double yi = rule.getOutput().equalsIgnoreCase("Stunting") ? 1 : 0;
            double alpha = calculateAlpha(rule, balita);

            numerator += alpha * yi;
            denominator += alpha;
        }

        // Menghitung output akhir y
        return numerator / denominator;
    }

    private double calculateAlpha(FuzzyRule rule, Balita balita) {
        // Implementasikan perhitungan untuk αi, misalnya berdasarkan jarak ke batas interval
        double usiaAlpha = 1.0; // Misalnya, usia sudah sesuai dengan kondisi
        double beratBadanAlpha = (balita.getBeratBadan() - rule.getBeratBadanMin()) /
                (rule.getBeratBadanMax() - rule.getBeratBadanMin());
        double tinggiBadanAlpha = (balita.getTinggiBadan() - rule.getTinggiBadanMin()) /
                (rule.getTinggiBadanMax() - rule.getTinggiBadanMin());

        return Math.min(Math.min(usiaAlpha, beratBadanAlpha), tinggiBadanAlpha);
    }

    public double calculateFinalOutputFromRules(List<FuzzyRule> matchingRules) {
        double numerator = 0.0;
        double denominator = 0.0;

        for (FuzzyRule rule : matchingRules) {
            double yi = rule.getOutput().equalsIgnoreCase("Stunting") ? 1 : 0;
            double alpha = 1.0; // Asumsikan αi = 1 untuk setiap rule yang cocok

            numerator += alpha * yi;
            denominator += alpha;
        }

        if (denominator == 0) {
            throw new ArithmeticException("Denominator is zero, can't divide by zero.");
        }

        return numerator / denominator;
    }
    public String getStuntingConclusion(double finalOutput) {
        if (finalOutput >= 0.8) {
            return "Anak tersebut berada dalam kategori Stunting.";
        } else {
            return "Anak tersebut berada dalam kategori Normal.";
        }
    }

}




