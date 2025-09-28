package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
