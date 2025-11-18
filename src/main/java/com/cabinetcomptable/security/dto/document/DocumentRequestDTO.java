package com.cabinetcomptable.security.dto.document;

import com.cabinetcomptable.security.entity.enums.DocumentType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DocumentRequestDTO {

    @NotBlank(message = "Must Be Not Empty")
    private String documentNumber;
    private DocumentType type;
    private String category;
    private LocalDate documentDate;

    @DecimalMin("0.0")
    private Double cost;
    private String supplier;
    private String document;

    private String comment;
    private Long companyId;

}
