package com.houlaidahamada.musicmanager.controller;

import com.houlaidahamada.musicmanager.dto.user.AuthResponseDto;
import com.houlaidahamada.musicmanager.dto.user.RefreshTokenDto;
import com.houlaidahamada.musicmanager.dto.user.UserLoginDto;
import com.houlaidahamada.musicmanager.dto.user.UserRegisterDto;
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

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody UserRegisterDto registerDto) {
        AuthResponseDto response = authenticationService.register(registerDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody UserLoginDto loginDto) {
        AuthResponseDto response = authenticationService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDto> refresh(@Valid @RequestBody RefreshTokenDto refreshTokenDto) {
        AuthResponseDto response = authenticationService.refresh(refreshTokenDto.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
