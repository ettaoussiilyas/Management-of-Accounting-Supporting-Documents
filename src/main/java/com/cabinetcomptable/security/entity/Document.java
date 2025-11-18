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

@Entity(name = "document")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "document_number")
    private String documentNumber;

    private DocumentType type;

    private String category;

    @Column(name = "document_date")
    private LocalDate documentDate;

    private Double cost;

    private String supplier;

    private String document;

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
