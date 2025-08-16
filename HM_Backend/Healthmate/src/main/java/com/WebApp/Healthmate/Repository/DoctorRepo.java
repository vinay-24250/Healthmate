package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepo extends JpaRepository<Doctor, Long> {

}
