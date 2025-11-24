package com.cabinetcomptable.security.dto.user;

import com.cabinetcomptable.security.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String email;
    private String password;
    private UserRole role;
    private String fullName;
    private Long companyId;
}