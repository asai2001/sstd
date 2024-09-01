package com.stuntingdetection.stuntingdetection.Controller;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.UserRepo;
import com.stuntingdetection.stuntingdetection.Service.Impl.UserLoginServices;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @Autowired
    UserLoginServices userLoginServices;

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

}
