package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.user.AuthResponseDto;
import com.houlaidahamada.musicmanager.dto.user.UserLoginDto;
import com.houlaidahamada.musicmanager.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginDto loginDto) {
        String token = authenticationService.login(loginDto);
        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
