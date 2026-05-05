package com.houlaidahamada.musicmanager.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginDto {

    private String username;
    private String email;

    @NotBlank
    private String password;

    public boolean hasCredential() {
        return (username != null && !username.isBlank()) || (email != null && !email.isBlank());
    }
}