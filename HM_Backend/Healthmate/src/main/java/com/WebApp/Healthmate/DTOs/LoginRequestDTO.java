package com.WebApp.Healthmate.DTOs;

import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDateTime;

public class LoginRequestDTO {

    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginRequestDTO() {}

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
