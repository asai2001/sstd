package com.stuntingdetection.stuntingdetection.Service;

import com.stuntingdetection.stuntingdetection.Entity.FuzzyRule;
import com.stuntingdetection.stuntingdetection.Repository.FuzzyRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuzzyRuleService {

    @Autowired
    private FuzzyRuleRepository fuzzyRuleRepository;

    public List<FuzzyRule> getAllRules() {
        return fuzzyRuleRepository.findAll();
    }

    public FuzzyRule addRule(FuzzyRule rule) {
        return fuzzyRuleRepository.save(rule);
    }

    public void deleteRule(Long id) {
        fuzzyRuleRepository.deleteById(id);
    }

    public List<FuzzyRule> findMatchingRule(String usia, double beratBadan, double tinggiBadan) {
        return fuzzyRuleRepository.findByUsiaAndBeratBadanMinLessThanEqualAndBeratBadanMaxGreaterThanEqualAndTinggiBadanMinLessThanEqualAndTinggiBadanMaxGreaterThanEqual(
                usia, beratBadan, beratBadan, tinggiBadan, tinggiBadan);
    }

    public FuzzyRule updateRule(Long id, FuzzyRule ruleDetails) {
        FuzzyRule rule = fuzzyRuleRepository.findById(id).orElse(null);
        if (rule != null) {
            rule.setUsia(ruleDetails.getUsia());
            rule.setBeratBadanCondition(ruleDetails.getBeratBadanCondition());
            rule.setBeratBadanMin(ruleDetails.getBeratBadanMin());
            rule.setBeratBadanMax(ruleDetails.getBeratBadanMax());
            rule.setTinggiBadanCondition(ruleDetails.getTinggiBadanCondition());
            rule.setTinggiBadanMin(ruleDetails.getTinggiBadanMin());
            rule.setTinggiBadanMax(ruleDetails.getTinggiBadanMax());
            rule.setOutput(ruleDetails.getOutput());
            return fuzzyRuleRepository.save(rule);
        }
        return null;
    }
}