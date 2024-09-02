package com.stuntingdetection.stuntingdetection.Service;

import com.stuntingdetection.stuntingdetection.Entity.Balita;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.BalitaRepository;
import com.stuntingdetection.stuntingdetection.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalitaService {

    @Autowired
    private BalitaRepository balitaRepository;

    @Autowired
    private UserRepo userRepository;

    public Balita addBalitaToUser(Balita balita, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        balita.setUser(user);
        return balitaRepository.save(balita);
    }

    public List<Balita> getBalitaByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        return balitaRepository.findByUser(user);
    }

    public Balita getBalitaById(Long balitaId) {
        return balitaRepository.findById(balitaId)
                .orElseThrow(() -> new IllegalArgumentException("Balita not found with id: " + balitaId));
    }


    public Balita updateBalita(Long balitaId, Balita balitaDetails) {
        Balita balita = balitaRepository.findById(balitaId)
                .orElseThrow(() -> new IllegalArgumentException("Balita not found with id: " + balitaId));

        // Update fields
        balita.setUsia(balitaDetails.getUsia());
        balita.setNama(balitaDetails.getNama());
        balita.setKeterangan(balitaDetails.getKeterangan());
        balita.setSkorStunting(balitaDetails.getSkorStunting());

        return balitaRepository.save(balita);
    }

    public void deleteBalita(Long balitaId) {
        Balita balita = balitaRepository.findById(balitaId)
                .orElseThrow(() -> new IllegalArgumentException("Balita not found with id: " + balitaId));
        balitaRepository.delete(balita);
    }

    public Balita saveStuntingResult(Long balitaId, double skorStunting, String keterangan) {
        Balita balita = balitaRepository.findById(balitaId)
                .orElseThrow(() -> new IllegalArgumentException("Balita not found with id: " + balitaId));

        balita.setSkorStunting(Double.toString(skorStunting));
        balita.setKeterangan(keterangan);

        return balitaRepository.save(balita);
    }

}
