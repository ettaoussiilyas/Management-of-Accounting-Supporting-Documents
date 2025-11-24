package com.cabinetcomptable.security.controller;

import com.cabinetcomptable.security.dto.auth.LoginRequest;
import com.cabinetcomptable.security.dto.auth.ApiResponse;
import com.cabinetcomptable.security.dto.auth.AuthResponse;
import com.cabinetcomptable.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.authenticate(request);
        return ResponseEntity.ok(ApiResponse.success(authResponse, "Connexion Success"));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<String>> health() {
        return ResponseEntity.ok(ApiResponse.success("OK", "Authentification Service Work"));
    }
}