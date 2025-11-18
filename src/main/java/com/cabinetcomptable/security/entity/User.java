package com.cabinetcomptable.security.entity;

import com.cabinetcomptable.security.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
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

    @Column(name = "full_name", unique = true)
    private String fullName;

    private UserStatus status;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @PrePersist
    protected void onCreate(){
        creationDate = LocalDate.now();
    }
}
