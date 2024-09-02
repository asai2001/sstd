package com.stuntingdetection.stuntingdetection.Repository;


import com.stuntingdetection.stuntingdetection.Entity.Balita;
import com.stuntingdetection.stuntingdetection.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalitaRepository extends JpaRepository<Balita, Long> {
    List<Balita> findByUser(User user);
}

