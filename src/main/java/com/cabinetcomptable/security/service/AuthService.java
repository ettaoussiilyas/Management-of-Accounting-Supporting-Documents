package com.cabinetcomptable.security.service;

import com.cabinetcomptable.security.dto.auth.LoginRequest;
import com.cabinetcomptable.security.dto.auth.AuthResponse;
import com.cabinetcomptable.security.entity.User;
import com.cabinetcomptable.security.entity.SocieteUser;
import com.cabinetcomptable.security.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthResponse authenticate(LoginRequest request) {
        try {
            log.info("Tentative de connexion pour: {}", request.getEmail());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            User user = userService.findByEmail(request.getEmail());

            if (!user.getStatus().name().equals("ACTIVE")) {
                throw new InvalidCredentialsException();
            }

            Long companyId = extractCompanyId(user);

            String jwtToken = jwtService.generateToken(
                    (org.springframework.security.core.userdetails.UserDetails) authentication.getPrincipal(),
                    user.getRole().name(),
                    companyId
            );

            log.info("Connexion réussie pour: {}", request.getEmail());

            return new AuthResponse(jwtToken, user.getEmail(), user.getRole().name(),
                    companyId, user.getFullName());

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            log.warn("Échec connexion pour: {}", request.getEmail());
            throw new InvalidCredentialsException();
        }
    }

    private Long extractCompanyId(User user) {
        if (user instanceof SocieteUser) {
            SocieteUser societeUser = (SocieteUser) user;
            return societeUser.getLinkedCompany() != null ?
                    societeUser.getLinkedCompany().getId() : null;
        }
        return null;
    }
}