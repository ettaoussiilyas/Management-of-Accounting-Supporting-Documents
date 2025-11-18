package com.cabinetcomptable.security.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ice;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

}
