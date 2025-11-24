package com.cabinetcomptable.security.entity;

import com.cabinetcomptable.security.entity.enums.UserStatus;
import com.cabinetcomptable.security.entity.enums.UserRole; // AJOUTER cet import
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    private UserRole role;

    @Column(name = "full_name")
    private String fullName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @PrePersist
    protected void onCreate(){
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
    }
}