package com.cabinetcomptable.security.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "company")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sociale_raison")
    private String socialeRaison;

    private String ice;

    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "linkedCompany", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SocieteUser> employees;
}