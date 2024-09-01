package com.stuntingdetection.stuntingdetection.Repository;

import com.stuntingdetection.stuntingdetection.Entity.FuzzyRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FuzzyRuleRepository extends JpaRepository<FuzzyRule, Long> {
    List<FuzzyRule> findByUsiaAndBeratBadanMinLessThanEqualAndBeratBadanMaxGreaterThanEqualAndTinggiBadanMinLessThanEqualAndTinggiBadanMaxGreaterThanEqual(
            String usia, double beratBadanMin, double beratBadanMax, double tinggiBadanMin, double tinggiBadanMax);
}