package com.stuntingdetection.stuntingdetection.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column (name = "nama")
    private String nama;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "no_handphone")
    private String noHandphone;//change to string because add zero in first number

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))

    private List<Role> roles;
}
