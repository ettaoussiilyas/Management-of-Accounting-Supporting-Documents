package com.cabinetcomptable.security.dto.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class CompanyRequestDTO {

    @NotBlank(message = "Must entry a valide Company Name")
    private String socialeRaison;

    @NotBlank(message = "Must entry a valide ice")
    private String ice;

    private String address;

    private String phoneNumber;

    @Email(message = "Must Entry a valide email format")
    private String email;

}
