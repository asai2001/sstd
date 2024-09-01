package com.stuntingdetection.stuntingdetection.Controller;

import com.stuntingdetection.stuntingdetection.Entity.Balita;
//import com.stuntingdetection.stuntingdetection.Service.Fuzzification;
import com.stuntingdetection.stuntingdetection.Service.Inference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StuntingController {

//    @Autowired
//    private Fuzzification fuzzification;
    @Autowired
    private Inference inference;

    @PostMapping("/api/deteksi-stunting/deteksi")
    public String deteksiStunting(@RequestBody Balita balita) {
        return inference.inferensi(balita);
    }
}

