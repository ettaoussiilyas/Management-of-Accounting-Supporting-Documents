package com.cabinetcomptable.security.controller;

import com.cabinetcomptable.security.dto.auth.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    @GetMapping("/public")
    public ResponseEntity<ApiResponse<String>> publicEndpoint() {
        return ResponseEntity.ok(ApiResponse.success("Ceci est un endpoint public", "Succès"));
    }

    @GetMapping("/protected")
    public ResponseEntity<ApiResponse<String>> protectedEndpoint() {
        return ResponseEntity.ok(ApiResponse.success("Ceci est un endpoint protégé - Accès autorisé", "Succès"));
    }

    @GetMapping("/comptable-only")
    @PreAuthorize("hasRole('ACCOUNTANT')")
    public ResponseEntity<ApiResponse<String>> comptableOnly() {
        return ResponseEntity.ok(ApiResponse.success("Ceci est réservé aux comptables", "Succès"));
    }

    @GetMapping("/societe-only")
    @PreAuthorize("hasRole('SOCIETE')")
    public ResponseEntity<ApiResponse<String>> societeOnly() {
        return ResponseEntity.ok(ApiResponse.success("Ceci est réservé aux sociétés", "Succès"));
    }

    @GetMapping("/user-info")
    public ResponseEntity<ApiResponse<Map<String, String>>> userInfo() {
        return ResponseEntity.ok(ApiResponse.success(
                Map.of("message", "Informations utilisateur", "status", "authentifié"),
                "Informations utilisateur"
        ));
    }
}