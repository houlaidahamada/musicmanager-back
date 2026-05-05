package com.houlaidahamada.musicmanager.service;

import com.houlaidahamada.musicmanager.dto.user.UserLoginDto;
import com.houlaidahamada.musicmanager.dto.user.UserRegisterDto;
import com.houlaidahamada.musicmanager.entity.User;
import com.houlaidahamada.musicmanager.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtTokenProvider jwtTokenProvider,
                                 UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userMapper = userMapper;
    }

    public String register(UserRegisterDto registerDto) {
        // Check if username already exists
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }

        // Check if email already exists
        if (userRepository.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        // Create user entity from DTO
        User user = userMapper.toEntity(registerDto);

        // Hash the password
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        // Save the user
        User savedUser = userRepository.save(user);

        // Generate and return JWT token
        return jwtTokenProvider.createToken(savedUser.getUsername(), savedUser.getEmail(), savedUser.getId());
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
