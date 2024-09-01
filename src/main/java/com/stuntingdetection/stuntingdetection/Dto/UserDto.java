package com.stuntingdetection.stuntingdetection.Dto;



import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class UserDto {
    private int userId;
    private String nama;
    private String email;
    private String password;
    private String alamat;
    private String noHandphone;
    private int roleId;
}
