package com.cabinetcomptable.security.mapper;


import com.cabinetcomptable.security.dto.company.CompanyRequestDTO;
import com.cabinetcomptable.security.dto.company.CompanyResponseDTO;
import com.cabinetcomptable.security.dto.company.CompanySimpleDTO;
import com.cabinetcomptable.security.entity.Company;
import org.mapstruct.*;

        import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface CompanyMapper {

    CompanyResponseDTO toResponseDTO(Company company);

    CompanySimpleDTO toSimpleDTO(Company company);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Company toEntity(CompanyRequestDTO dto);

    List<CompanyResponseDTO> toResponseDTOList(List<Company> companies);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    void updateEntityFromDTO(CompanyRequestDTO dto, @MappingTarget Company company);
}