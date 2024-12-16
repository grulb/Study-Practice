package com.example.TownLibrary.Requests;

public class LoginRequest {
    private String email;
    private String password;
    private int status;
    private String role;
    private Long userId;

    public LoginRequest(int status, String role, Long userId) {
        this.status = status;
        this.role = role;
        this.userId = userId;
    }

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

    public int getStatus() {
        return status;
    }

    public String getRole() {
        return role;
    }

    public Long getUserId() {
        return userId;
    }
}