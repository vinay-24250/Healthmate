package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.DTOs.AppointmentRequestDTO;
import com.WebApp.Healthmate.DTOs.AppointmentResponseDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import com.WebApp.Healthmate.Model.Doctor;
import com.WebApp.Healthmate.Repository.AppointmentRepo;
import com.WebApp.Healthmate.Repository.DoctorRepo;
import com.WebApp.Healthmate.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    public AppointmentService(AppointmentRepo appointmentRepo) {
        this.appointmentRepo = appointmentRepo;
    }

    public AppointmentResponseDTO bookAppointment(AppointmentRequestDTO request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = authRepo.findByEmail(email);
        System.out.println("user");


        Doctor doctor = doctorRepo.findById(request.getDocId()).orElseThrow(() -> new RuntimeException("Doctor is null"));

        Appointment appointment = new Appointment();

        appointment.setUser(user);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setStatus(AppointmentStatus.PENDING);
        appointment.setReason(request.getReason());

        Appointment savedAppointement = appointmentRepo.save(appointment);
        return new AppointmentResponseDTO(savedAppointement);
    }

    public List<AppointmentResponseDTO> getDoctorAppointments(String email) {

        AppUser user = authRepo.findByEmail(email).getDoctor().getUser();
       List<Appointment> appointmentList = appointmentRepo.findByDoctor_DocId(user.getDoctor().getDocId());

        return appointmentList.stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());

    }

    public AppointmentResponseDTO updateStatus(Long appointmentId, AppointmentStatus status) {

      Appointment appointment = appointmentRepo.findById(appointmentId).orElseThrow(() -> new RuntimeException("Appointment Not found"));

      appointment.setStatus(status);

      return new AppointmentResponseDTO(appointmentRepo.save(appointment));
    }

    public List<AppointmentResponseDTO> getAppointmentsByStatus(AppointmentStatus status) {

        List<Appointment> appointmentList = appointmentRepo.findByStatus(status);

        return appointmentList.stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());
    }

    public List<AppointmentResponseDTO> getAppointments(String email) {

        AppUser user = authRepo.findByEmail(email);
        List<Appointment> appointmentList = appointmentRepo.findByUser_Id(user.getId());

        return appointmentList.stream()
                .map(AppointmentResponseDTO::new)
                .collect(Collectors.toList());

    }
}

