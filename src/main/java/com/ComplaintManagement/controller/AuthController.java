package com.ComplaintManagement.controller;

import com.ComplaintManagement.dto.AuthResponse;
import com.ComplaintManagement.dto.LoginRequest;
import com.ComplaintManagement.dto.RegisterRequest;
import com.ComplaintManagement.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid  @RequestBody RegisterRequest registerRequest)
    {
        return new ResponseEntity<>(authService.registerUser(registerRequest), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid  @RequestBody LoginRequest loginRequest)
    {
        return new ResponseEntity<>(authService.login(loginRequest), HttpStatus.OK);
    }
}
