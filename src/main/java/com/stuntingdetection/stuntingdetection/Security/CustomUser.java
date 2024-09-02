package com.stuntingdetection.stuntingdetection.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private final String userId;
    private final String fullname;

    public CustomUser(String userId, String email, String password, String fullname, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
        this.userId = userId;
        this.fullname = fullname;
    }

    public String getUserId() {
        return userId;
    }

    public String getFullname() {
        return fullname;
    }
}