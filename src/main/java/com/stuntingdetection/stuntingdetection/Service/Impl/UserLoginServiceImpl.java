package com.stuntingdetection.stuntingdetection.Service.Impl;


import com.stuntingdetection.stuntingdetection.Dto.UserDto;
import com.stuntingdetection.stuntingdetection.Entity.Role;
import com.stuntingdetection.stuntingdetection.Entity.User;
import com.stuntingdetection.stuntingdetection.Repository.RoleRepo;
import com.stuntingdetection.stuntingdetection.Repository.UserRepo;
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
    public User findByNama(String nama) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null){
            logger.error("user not found");
        } else {
            logger.info(email + "user found ");
        }
        Collection <SimpleGrantedAuthority > simpleGrantedAuthorities = new ArrayList<>();
        user.getRoles().forEach(roles -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roles.getRole())));
        return new org.springframework.security.core.userdetails.
                User(user.getEmail(), user.getPassword(), simpleGrantedAuthorities);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User saveUserDefault(UserDto user) {
        User saveUser = new User();
        saveUser.setEmail(user.getEmail());
        List<Role> getRoleById = roleRepo.findByRoleId(2);
        saveUser.setRoles(getRoleById);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(saveUser);
    }

    @Override
    public User registerSelectRole(UserDto userLogin, int roleId) {
        User saveUser = new User();
        saveUser.setNama(userLogin.getNama());
        saveUser.setEmail(userLogin.getEmail());
        List<Role> getRoleById = roleRepo.findByRoleId(roleId);
        saveUser.setRoles(getRoleById);
        saveUser.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userRepository.save(saveUser);
    }

    @Override
    public User registerSelectRole2(UserDto userLogin) {
        User saveUser = new User();
        saveUser.setNama(userLogin.getNama());
        saveUser.setEmail(userLogin.getEmail());
        saveUser.setNoHandphone(userLogin.getNoHandphone());
        saveUser.setAlamat(userLogin.getAlamat());
        List<Role> getRoleById = roleRepo.findByRoleId(userLogin.getRoleId());
        saveUser.setRoles(getRoleById);
        saveUser.setPassword(passwordEncoder.encode(userLogin.getPassword()));
        return userRepository.save(saveUser);
    }



}
