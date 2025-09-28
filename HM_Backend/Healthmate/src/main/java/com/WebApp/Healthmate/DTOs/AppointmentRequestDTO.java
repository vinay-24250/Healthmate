package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.Appointment;
import com.WebApp.Healthmate.Model.AppointmentStatus;

import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private Long docId;
    public AppointmentRequestDTO() {
    }

    public AppointmentRequestDTO(Long docId, AppointmentStatus status, String reason, LocalDateTime appointmentTime) {
        this.docId = docId;
        this.status = status;
        this.reason = reason;
        this.appointmentTime = appointmentTime;
    }

    public AppointmentRequestDTO(Appointment savedAppointement) {
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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
