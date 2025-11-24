package com.cabinetcomptable.security.dto.company;


import lombok.Data;

@Data
public class CompanySimpleDTO {

    private Long id;
    private String socialeRaison;
    private String ice;
    private String address;
    private String phoneNumber;
    private String email;

}
