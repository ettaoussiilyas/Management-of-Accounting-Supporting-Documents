package com.cabinetcomptable.security.dto.user;

import com.cabinetcomptable.security.entity.enums.UserStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserResponseDTO {

    private Long id;
    private String email;
    private String role;
    private String fullName;
    private UserStatus status;
    private LocalDate creationDate;

}
