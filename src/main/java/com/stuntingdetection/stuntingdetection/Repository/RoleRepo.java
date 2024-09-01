package com.stuntingdetection.stuntingdetection.Repository;


import com.stuntingdetection.stuntingdetection.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, Integer> {
    List<Role> findByRoleId(int roleId);
}


