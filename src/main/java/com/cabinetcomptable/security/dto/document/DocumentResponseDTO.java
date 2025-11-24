package com.cabinetcomptable.security.dto.document;

import com.cabinetcomptable.security.entity.enums.DocumentStatus;
import com.cabinetcomptable.security.entity.enums.DocumentType;
import com.cabinetcomptable.security.dto.company.CompanySimpleDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentResponseDTO {
    private Long id;
    private String documentNumber;
    private DocumentType type;
    private String category;
    private LocalDate documentDate;
    private Double cost;
    private String supplier;
    private String document; // original filename
    private String publicId;
    private String documentUrl;
    private DocumentStatus status;
    private LocalDate validationDate;
    private String comment;
    private LocalDate creationDate;
    private LocalDate modificationDate;
    private CompanySimpleDTO company;
}