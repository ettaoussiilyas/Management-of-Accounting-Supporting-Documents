package com.cabinetcomptable.security.dto.user;

import com.cabinetcomptable.security.entity.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {

    @Email(message = "Must entry a valide email format")
    private String email;

    @NotBlank(message = "Must Be Not Empty")
    private String password;
    private String role;

    @NotBlank(message = "Must Be Not Empty")
    private String fullName;

    private UserStatus status;
    private Long linkedCompany;
}
