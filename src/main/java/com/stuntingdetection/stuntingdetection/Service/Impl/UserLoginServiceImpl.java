package com.stuntingdetection.stuntingdetection.Service.Impl;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.Role;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.RoleRepo;
import com.stuntingdetection.stuntingdetection.Repository.UserRepo;
import com.stuntingdetection.stuntingdetection.Security.CustomUser;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class UserLoginServiceImpl implements UserLoginServices, UserDetailsService {
    @Autowired
    public final UserRepo userRepository;
    @Autowired
    public final RoleRepo roleRepo;
    public final PasswordEncoder passwordEncoder;
    private final Logger logger = LogManager.getLogger(UserLoginServiceImpl.class);



    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.error("user not found");
            throw new UsernameNotFoundException("User not found");
        } else {
            logger.info(username + " user found ");
        }
        Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(roles -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roles.getRole())));

        // Kembalikan CustomUser dengan userId dan fullname
        return new CustomUser(user.getUserId().toString(), user.getEmail(), user.getPassword(), user.getFullname(), simpleGrantedAuthorities);
    }

    @Override
    public User registerSelectRole(UserDto userLogin, int roleId) {
        User saveUser = new User();
        saveUser.setFullname(userLogin.getFullname());
        saveUser.setEmail(userLogin.getEmail());
        List<Role> getRoleById = roleRepo.findByRoleId(roleId);
        saveUser.setRoles(getRoleById);
        saveUser.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userRepository.save(saveUser);
    }

    @Override
    public User registerSelectRole2(UserDto userLogin) {
        User saveUser = new User();
        saveUser.setUsername(userLogin.getUsername());
        saveUser.setFullname(userLogin.getFullname());
        saveUser.setEmail(userLogin.getEmail());
        saveUser.setNoHandphone(userLogin.getNoHandphone());
        saveUser.setAlamat(userLogin.getAlamat());
        List<Role> getRoleById = roleRepo.findByRoleId(userLogin.getRoleId());
        saveUser.setRoles(getRoleById);
        saveUser.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userRepository.save(saveUser);
    }


}
