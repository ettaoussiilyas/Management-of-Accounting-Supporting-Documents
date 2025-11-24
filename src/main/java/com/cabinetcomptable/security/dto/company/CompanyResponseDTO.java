package com.cabinetcomptable.security.dto.company;

import com.cabinetcomptable.security.dto.user.UserSimpleDTO;
import lombok.Data;

import java.util.List;

@Data
public class CompanyResponseDTO {

    private Long id;
    private String socialeRaison;
    private String ice;
    private String address;
    private String phoneNumber;
    private String email;
    private List<UserSimpleDTO> employees;
}
