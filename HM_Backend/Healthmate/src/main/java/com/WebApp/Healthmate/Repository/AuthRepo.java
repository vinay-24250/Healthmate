package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthRepo extends JpaRepository<AppUser,Long>{

   AppUser findByEmail(String email);

    boolean existsByEmail(String email);


    List<AppUser> findByRoleAndDoctor_Specialization(Role role, String specialization);

    List<AppUser> findByRole(Role role);
}
