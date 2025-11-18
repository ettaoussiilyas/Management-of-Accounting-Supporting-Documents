package com.cabinetcomptable.security.mapper;

import com.cabinetcomptable.security.dto.document.DocumentRequestDTO;
import com.cabinetcomptable.security.dto.document.DocumentResponseDTO;
import com.cabinetcomptable.security.entity.Document;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface DocumentMapper {

    @Mapping(target = "documentPath", source = "document")
    DocumentResponseDTO toResponseDTO(Document document);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    @Mapping(target = "validationDate", ignore = true)
    @Mapping(target = "company", ignore = true) // Géré manuellement dans le service
    Document toEntity(DocumentRequestDTO dto);

    List<DocumentResponseDTO> toResponseDTOList(List<Document> documents);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "modificationDate", ignore = true)
    @Mapping(target = "validationDate", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateEntityFromDTO(DocumentRequestDTO dto, @MappingTarget Document document);
}