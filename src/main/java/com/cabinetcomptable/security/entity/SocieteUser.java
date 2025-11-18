package com.cabinetcomptable.security.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SOCIETE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SocieteUser extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "societe_id")
    private Company linkedCompany;

}
