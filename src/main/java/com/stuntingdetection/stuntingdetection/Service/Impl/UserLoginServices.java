package com.stuntingdetection.stuntingdetection.Service.Impl;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.User;

public interface UserLoginServices {

    public User findByEmail(String email);

    public User findByUsername(String username);
//    public User saveUser(User user);
//
//    User saveUserDefault(UserDto user);

    public User registerSelectRole (UserDto userDto, int roleId); // new
    public User registerSelectRole2 (UserDto userDto); // new
}
