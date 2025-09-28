package com.WebApp.Healthmate.DTOs;

public class DoctorResponseDTO {

    private  Long docId;
    private String fullName;
    private String email;
    private String specialization;
    private String qualification;
    private String hospitalName;

    public DoctorResponseDTO(Long docId, String fullName, String email, String specialization, String qualification, String hospitalName) {
        this.docId = docId;
        this.hospitalName = hospitalName;
        this.qualification = qualification;
        this.specialization = specialization;
        this.email = email;
        this.fullName = fullName;
    }




    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
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
