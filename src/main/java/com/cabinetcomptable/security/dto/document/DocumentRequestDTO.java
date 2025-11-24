package com.cabinetcomptable.security.dto.document;

import com.cabinetcomptable.security.entity.enums.DocumentType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentRequestDTO {
    private String documentNumber;
    private DocumentType type;
    private String category;
    private LocalDate documentDate;
    private Double cost;
    private String supplier;
    private String comment;
}