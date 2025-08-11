package com.WebApp.Healthmate.DTOs;

public class DoctorResponseDTO {

    private  Long doc_id;
    private String fullName;
    private String email;
    private String specialization;

    public DoctorResponseDTO(Long doc_id, String fullName, String email, String specialization, String qualification, String hospitalName) {
        this.doc_id = doc_id;
        this.hospitalName = hospitalName;
        this.qualification = qualification;
        this.specialization = specialization;
        this.email = email;
        this.fullName = fullName;
    }

    private String qualification;
    private String hospitalName;


    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
