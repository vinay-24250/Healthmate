package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.DTOs.DoctorResponseDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser,Long>{

   Optional<AppUser> findByEmail(String email);

    boolean existsByEmail(String email);


    List<AppUser> findByRoleAndDoctor_Specialization(Role role, String specialization);
}
