package com.stuntingdetection.stuntingdetection.Repository;


import com.stuntingdetection.stuntingdetection.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
    public List<User> findAll();
//    User findByUsername(String Username);

    User findByEmail(String Email);
    public User findByNama(String nama);
//    public User findByUserId(int userId);
//    public List<User> findByUserid(int userId);
//public User findByUsername(String username);
}
