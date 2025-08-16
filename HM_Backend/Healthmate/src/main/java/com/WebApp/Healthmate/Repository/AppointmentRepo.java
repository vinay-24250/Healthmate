package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
}
