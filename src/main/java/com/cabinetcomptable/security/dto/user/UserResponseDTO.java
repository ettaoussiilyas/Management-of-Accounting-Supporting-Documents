package com.cabinetcomptable.security.dto.user;

import com.cabinetcomptable.security.entity.enums.UserStatus;
import com.cabinetcomptable.security.entity.enums.UserRole;
import com.cabinetcomptable.security.dto.company.CompanySimpleDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {
    private Long id;
    private String email;
    private UserRole role;
    private String fullName;
    private UserStatus status;
    private LocalDate creationDate;
    private CompanySimpleDTO company;
}