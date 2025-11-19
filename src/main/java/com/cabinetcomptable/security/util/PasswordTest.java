package com.cabinetcomptable.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "password123";
        String encodedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMye.KdwjB1.EB6a2WcY4nHtGp4p2E2Wz7a";

        System.out.println("Mot de passe brut: " + rawPassword);
        System.out.println("Mot de passe hashé: " + encodedPassword);
        System.out.println("Correspondance: " + encoder.matches(rawPassword, encodedPassword));

        String newHash = encoder.encode("password123");
        System.out.println("Nouveau hash: " + newHash);
    }
}