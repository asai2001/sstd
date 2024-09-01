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

    public String inferensi(Balita balita) {
        List<FuzzyRule> matchingRules = fuzzyRuleService.findMatchingRule(
                balita.getUsia(),
                balita.getBeratBadan(),
                balita.getTinggiBadan()
        );

        if (matchingRules.isEmpty()) {
            return "Tidak ditemukan aturan yang sesuai";
        } else {
            return matchingRules.get(0).getOutput(); // Menggunakan aturan pertama yang cocok
        }
    }
}


