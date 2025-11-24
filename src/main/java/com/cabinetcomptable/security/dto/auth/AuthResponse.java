package com.cabinetcomptable.security.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponse {

    private String token;
    private String type;
    private String email;
    private String role;
    private Long companyId;
    private String fullName;
    private LocalDateTime expiresAt;

    public AuthResponse(String token, String email, String role, Long companyId, String fullName){
        this.token = token;
        this.type = "Bearer";
        this.email = email;
        this.role = role;
        this.companyId = companyId;
        this.fullName = fullName;
        this.expiresAt = LocalDateTime.now().plusHours(24);
    }

}
