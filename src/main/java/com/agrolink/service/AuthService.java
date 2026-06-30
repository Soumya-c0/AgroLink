package com.agrolink.service;

import com.agrolink.dto.AuthenticationResponse;
import com.agrolink.dto.LoginRequest;
import com.agrolink.dto.RegisterRequest;
import com.agrolink.entity.User;
import com.agrolink.repository.UserRepository;
import com.agrolink.security.UserPrincipal;
import com.agrolink.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered.");
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .role(request.getRole())
                .enabled(true)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(new UserPrincipal(user));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Registration successful")
                .build();
    }

    public AuthenticationResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String jwtToken = jwtService.generateToken(new UserPrincipal(user));

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole().name())
                .message("Login successful")
                .build();
    }

}