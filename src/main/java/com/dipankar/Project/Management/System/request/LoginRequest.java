package com.dipankar.Project.Management.System.request;

import jakarta.validation.constraints.Email;

public class LoginRequest {
    @Email(message = "Email format is wrong")
    private String email;
    private String password;

    public LoginRequest(){}
    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
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

    @Override
    public String toString() {
        return "LoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
