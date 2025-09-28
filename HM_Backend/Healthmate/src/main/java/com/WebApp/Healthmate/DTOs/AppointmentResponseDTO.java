package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    private Long appointmentId;
    private String fullName;
    private String doctorName;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public AppointmentResponseDTO(Long appointmentId , String fullName, String doctorName, AppointmentStatus status, LocalDateTime appointmentTime, String reason) {
        this.appointmentId = appointmentId;
        this.fullName = fullName;
        this.doctorName = doctorName ;
        this.status = status;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
    }

    private AppointmentStatus status;
    private LocalDateTime appointmentTime;
    private String reason;

    public AppointmentResponseDTO(@NotNull Appointment savedAppointement) {
        this.fullName = savedAppointement.getUser().getFullName();
        this.doctorName = savedAppointement.getDoctor().getUser().getFullName();
        this.appointmentId = savedAppointement.getAppointmentId();
        this.appointmentTime = savedAppointement.getAppointmentTime();
        this.reason = savedAppointement.getReason();
        this.status = savedAppointement.getStatus();
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
