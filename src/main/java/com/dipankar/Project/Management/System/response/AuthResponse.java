package com.dipankar.Project.Management.System.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class AuthResponse {
    private String jwt;
    private String message;

    public AuthResponse(){}

    public AuthResponse(String jwt, String message) {
        this.jwt = jwt;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "jwt='" + jwt + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
