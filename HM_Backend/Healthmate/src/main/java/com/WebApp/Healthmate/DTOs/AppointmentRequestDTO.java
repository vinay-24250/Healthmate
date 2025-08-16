package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long doc_id;

    private String doctorName;

    public AppointmentRequestDTO() {
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public AppointmentRequestDTO(Long doctor_id, String doctorName, AppointmentStatus status, String reason, LocalDateTime appointmentTime) {
        this.doc_id = doc_id;
        this.doctorName = doctorName;
        this.status = status;
        this.reason = reason;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentRequestDTO(Appointment savedAppointement) {
    }

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
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

    private LocalDateTime appointmentTime;
    private String reason;
    private AppointmentStatus status = AppointmentStatus.PENDING;


}
