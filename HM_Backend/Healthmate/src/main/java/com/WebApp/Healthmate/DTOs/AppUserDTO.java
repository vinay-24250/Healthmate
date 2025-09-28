package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Doctor;
import com.WebApp.Healthmate.Model.Role;

public class AppUserDTO {

    private Long Id;
    private String email;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    private String fullname;
    private Role role;
    private Doctor doctor;


}
