package com.stuntingdetection.stuntingdetection.Controller;

import com.stuntingdetection.stuntingdetection.Dto.FuzzyInput;
import com.stuntingdetection.stuntingdetection.Entity.Balita;
//import com.stuntingdetection.stuntingdetection.Service.Fuzzification;
import com.stuntingdetection.stuntingdetection.Entity.FuzzyRule;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.BalitaRepository;
import com.stuntingdetection.stuntingdetection.Service.BalitaService;
import com.stuntingdetection.stuntingdetection.Service.Inference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StuntingController {

    @Autowired
    private Inference inference;

    @Autowired
    private BalitaRepository balitaRepository;

    @Autowired
    private BalitaService balitaService;
    @PostMapping("/api/deteksi-stunting/deteksi/{balitaId}")
    public ResponseEntity<Balita> deteksiStunting(@RequestBody Balita balitaInput, @PathVariable Long balitaId) {
        // Cari balita berdasarkan balitaId
        Balita balita = balitaService.getBalitaById(balitaId);

        if (balita == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Perhitungan hasil akhir
        double finalOutput = inference.calculateFinalOutput(balitaInput);
        String conclusion = inference.getStuntingConclusion(finalOutput);

        // Simpan hasil akhir ke balita
        balita.setKeterangan(conclusion.contains("Stunting") ? "Stunting" : "Normal");
        balita.setSkorStunting(String.valueOf(finalOutput));
        balitaService.updateBalita(balitaId, balita);

        // Kembalikan respons dengan detail balita
        return ResponseEntity.ok(balita);
    }


    @PostMapping("/api/deteksi-stunting/deteksi-by-condition/{balitaId}")
    public ResponseEntity<Balita> deteksiStuntingByCondition(@RequestBody FuzzyInput fuzzyInput, @PathVariable Long balitaId) {
        // Cari balita berdasarkan balitaId
        Balita balita = balitaService.getBalitaById(balitaId);

        if (balita == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Temukan rule yang sesuai dengan kondisi yang diinputkan
        List<FuzzyRule> matchingRules = inference.findRulesByConditions(
                fuzzyInput.getUsia(),
                fuzzyInput.getBeratBadanCondition(),
                fuzzyInput.getTinggiBadanCondition()
        );

        if (matchingRules.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Perhitungan hasil akhir menggunakan rules yang ditemukan
        double finalOutput = inference.calculateFinalOutputFromRules(matchingRules);
        String conclusion = inference.getStuntingConclusion(finalOutput);

        // Simpan hasil akhir ke balita
        balita.setKeterangan(conclusion.contains("Stunting") ? "Stunting" : "Normal");
        balita.setSkorStunting(String.valueOf(finalOutput));
        balitaService.updateBalita(balitaId, balita);

        // Kembalikan respons dengan detail balita
        return ResponseEntity.ok(balita);
    }

}

