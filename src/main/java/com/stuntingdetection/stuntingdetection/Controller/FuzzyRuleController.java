package com.stuntingdetection.stuntingdetection.Controller;

import com.stuntingdetection.stuntingdetection.Entity.FuzzyRule;
import com.stuntingdetection.stuntingdetection.Service.FuzzyRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FuzzyRuleController {

    @Autowired
    private FuzzyRuleService fuzzyRuleService;

    @GetMapping("/get-all-rule")
    public List<FuzzyRule> getAllRules() {
        return fuzzyRuleService.getAllRules();
    }

    @PostMapping("/add-rule")
    public ResponseEntity<FuzzyRule> addRule(@RequestBody FuzzyRule rule) {
        FuzzyRule newRule = fuzzyRuleService.addRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRule);
    }

    @PutMapping("update-rule/{id}")
    public ResponseEntity<FuzzyRule> updateRule(@PathVariable Long id, @RequestBody FuzzyRule ruleDetails) {
        FuzzyRule updatedRule = fuzzyRuleService.updateRule(id, ruleDetails);
        if (updatedRule != null) {
            return ResponseEntity.ok(updatedRule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete-rule/{id}")
    public ResponseEntity<Void> deleteRule(@PathVariable Long id) {
        fuzzyRuleService.deleteRule(id);
        return ResponseEntity.noContent().build();
    }
}
