package com.WebApp.Healthmate.Controller;

import com.WebApp.Healthmate.DTOs.AppointmentRequestDTO;
import com.WebApp.Healthmate.DTOs.AppointmentResponseDTO;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import com.WebApp.Healthmate.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/request")
    public ResponseEntity<AppointmentResponseDTO> requestAppointment(@RequestBody AppointmentRequestDTO request){
             return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    @GetMapping("/allAppointments")
    public List<AppointmentResponseDTO> getAllAppointments(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return appointmentService.getDoctorAppointments(email);
    }

    @GetMapping("/myAppointments")
    public List<AppointmentResponseDTO> getMyAppointments(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return appointmentService.getAppointments(email);
    }

    @PutMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponseDTO> updateStatus(@PathVariable Long appointmentId , @RequestParam AppointmentStatus status){
        AppointmentResponseDTO updatedAppointment = appointmentService.updateStatus(appointmentId ,status);
        return ResponseEntity.ok(updatedAppointment);
    }

    @GetMapping("/{status}")
    public List<AppointmentResponseDTO> getAppointmentByStatus(@PathVariable AppointmentStatus status){
        return appointmentService.getAppointmentsByStatus(status);
    }



}
