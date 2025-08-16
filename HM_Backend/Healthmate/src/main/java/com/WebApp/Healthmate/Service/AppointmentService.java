package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.DTOs.AppointmentRequestDTO;
import com.WebApp.Healthmate.DTOs.AppointmentResponseDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import com.WebApp.Healthmate.Model.Doctor;
import com.WebApp.Healthmate.Repository.AppointmentRepo;
import com.WebApp.Healthmate.Repository.DoctorRepo;
import com.WebApp.Healthmate.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println("user");


        Doctor doctor = doctorRepo.findById(request.getDoc_id()).orElseThrow(() -> new RuntimeException("Doctor is null"));

        Appointment appointment = new Appointment();

        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setReason(request.getReason());

        Appointment savedAppointement = appointmentRepo.save(appointment);
        return new AppointmentResponseDTO(savedAppointement);
    }
}

