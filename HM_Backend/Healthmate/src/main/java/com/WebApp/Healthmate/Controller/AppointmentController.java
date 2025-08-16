package com.WebApp.Healthmate.Controller;

import com.WebApp.Healthmate.DTOs.AppointmentRequestDTO;
import com.WebApp.Healthmate.DTOs.AppointmentResponseDTO;
import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import com.WebApp.Healthmate.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<AppointmentResponseDTO> requestAppointment(@RequestBody AppointmentRequestDTO request){
             return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    @GetMapping("/print")
    public void print(){
        System.out.println("Appoinrment");
    }

}
