package com.WebApp.Healthmate.Repository;

import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctor_DocId(Long doctor_id);

    List<Appointment> findByStatus(AppointmentStatus status);

    List<Appointment> findByUser_Id(long id);
}
