package com.stuntingdetection.stuntingdetection.Controller;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.Balita;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.UserRepo;
import com.stuntingdetection.stuntingdetection.Service.BalitaService;
import com.stuntingdetection.stuntingdetection.Service.Impl.UserLoginServices;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLoginServices userLoginServices;

    @Autowired
    private BalitaService balitaService;

    @SneakyThrows
    @PostMapping("user/submit")
    public ResponseEntity<User> submitUser(User user) {
        return new ResponseEntity<>(userRepo.save(user), HttpStatus.ACCEPTED);
    }

    @GetMapping("user/select-all")
    public ResponseEntity<?> usersList(){
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.ACCEPTED);
    }

    @PostMapping("user/register")
    public ResponseEntity<User> registerUser(@RequestBody UserDto userLogin) {
        User registeredUser = userLoginServices.registerSelectRole2(userLogin);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/api/balita/add/{userId}")
    public ResponseEntity<Balita> addBalita(@RequestBody Balita balita, @PathVariable Long userId) {
        Balita newBalita = balitaService.addBalitaToUser(balita, userId);
        return new ResponseEntity<>(newBalita, HttpStatus.CREATED);
    }

    @GetMapping("/api/balita/user/{userId}")
    public ResponseEntity<List<Balita>> getBalitaByUser(@PathVariable Long userId) {
        List<Balita> balitaList = balitaService.getBalitaByUser(userId);
        return new ResponseEntity<>(balitaList, HttpStatus.OK);
    }

    @PutMapping("/api/balita/edit/{balitaId}")
    public ResponseEntity<Balita> updateBalita(@PathVariable Long balitaId, @RequestBody Balita balitaDetails) {
        Balita updatedBalita = balitaService.updateBalita(balitaId, balitaDetails);
        return new ResponseEntity<>(updatedBalita, HttpStatus.OK);
    }

    @DeleteMapping("/api/balita/delete/{balitaId}")
    public ResponseEntity<Void> deleteBalita(@PathVariable Long balitaId) {
        balitaService.deleteBalita(balitaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
