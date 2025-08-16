package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class AppointmentResponseDTO {

    private Long appointmentId;
    private Long doc_id;
    private String fullName;
    private String doctorName;

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public AppointmentResponseDTO(Long appointmentId, Long doctor_id , String fullName, String doctorName, AppointmentStatus status, LocalDateTime appointmentTime, String reason) {
        this.doc_id = doc_id;
        this.appointmentId = appointmentId;
        this.fullName = fullName;
        this.doctorName = doctorName;
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
        this.doc_id = savedAppointement.getDoctor().getDoc_id();
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
