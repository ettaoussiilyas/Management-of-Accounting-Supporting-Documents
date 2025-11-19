package com.cabinetcomptable.security.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("ACCOUNTANT")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AccountantUser extends User{
}
