package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.user.UserLoginDto;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.repository.UserRepository;
import com.houlaidahamada.musicmanager.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public String login(UserLoginDto loginDto) {
        if (loginDto == null || !loginDto.hasCredential()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "username or email is required");
        }

        User user = userRepository.findByEmail(loginDto.getEmail())
                .or(() -> userRepository.findByUsername(loginDto.getUsername()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        return jwtTokenProvider.createToken(user.getUsername(), user.getEmail(), user.getId());
    }
}
