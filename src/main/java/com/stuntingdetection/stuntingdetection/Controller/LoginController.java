package com.stuntingdetection.stuntingdetection.Controller;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Service.Impl.UserLoginServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    UserLoginServiceImpl userLoginService;

//    @PostMapping("/registration")
//    public ResponseEntity<?> registration (@RequestBody User user ){
//        Map <String, String> map = new HashMap<>();
//        User userLogin = userLoginService.findByNama(user.getNama());
//        if (userLogin !=null){
//            map.put(user.getNama(), "username already exist");
//            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//        }else {
//            userLoginService.saveUser(user);
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/registration-select/{roleId}")
//    public ResponseEntity<?> registrationSelectRole (@RequestBody UserDto userDto, @PathVariable int roleId) throws Exception{
//        Map <String, String> map = new HashMap<>();
//        User userLogin = userLoginService.findByNama(userDto.getNama());
//        if (userLogin !=null){
//            map.put(userDto.getNama(), "username already exist");
//            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//        }else {
//            userLoginService.registerSelectRole(userDto, roleId);
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PostMapping("/registration-default")
//    public ResponseEntity<?> registrationDefault (@RequestBody UserDto userDto){
//        Map <String, String> map = new HashMap<>();
//        User userLogin = userLoginService.findByEmail(userDto.getEmail());
//        if (userLogin !=null){
//            map.put(userDto.getEmail(), "email already exist");
//            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
//        }else {
//            userLoginService.saveUserDefault(userDto);
//        }
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PostMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "user",
                    value = "{\"username\":\"asai2\",\n\"email\":\"kingmilf@gmail.com\",\n\"password\":\"abc\"}",
                    required = true,
                    paramType = "body",
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "string", mediaType = "application/json")
                            }))
    })
    public ResponseEntity<?> login (@RequestBody User user) {
        User userLogin = userLoginService.findByEmail(user.getEmail());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
