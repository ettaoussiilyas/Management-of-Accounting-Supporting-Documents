package com.cabinetcomptable.security.entity;

import com.cabinetcomptable.security.entity.enums.DocumentStatus;
import com.cabinetcomptable.security.entity.enums.DocumentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "doucument")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doucument_number")
    private String doucumentNumber;

    private DocumentType type;

    private String category;

    @Column(name = "doucument_date")
    private LocalDate doucumentDate;

    private Double cost;

    private String supplier;

    private String doucument;

    private DocumentStatus status;

    @Column(name = "validation_date")
    private LocalDate validationDate;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "modification_date")
    private LocalDate modificationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    protected void onCreate(){
        creationDate = LocalDate.now();
        if (this instanceof Document) modificationDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate(){
        modificationDate = LocalDate.now();
    }
}
