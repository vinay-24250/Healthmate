package com.WebApp.Healthmate.DTOs;

import com.WebApp.Healthmate.Model.Role;

public class LoginResponseDTO {

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponseDTO(Role role, String token) {
        this.role = role;
        this.token = token;
    }

    private String token;
}
