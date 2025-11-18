package com.cabinetcomptable.security.mapper;

import com.cabinetcomptable.security.dto.user.UserRequestDTO;
import com.cabinetcomptable.security.dto.user.UserResponseDTO;
import com.cabinetcomptable.security.dto.user.UserSimpleDTO;
import com.cabinetcomptable.security.entity.User;
import com.cabinetcomptable.security.entity.SocieteUser;
import com.cabinetcomptable.security.entity.AccountantUser;
import org.mapstruct.*;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserMapper {

    @Mapping(target = "company", source = "linkedCompany")
    UserResponseDTO toResponseDTO(User user);

    UserSimpleDTO toSimpleDTO(User user);

    List<UserResponseDTO> toResponseDTOList(List<User> users);

    List<UserSimpleDTO> toSimpleDTOList(List<User> users);

    default User toEntity(UserRequestDTO dto) {
        if ("SOCIETE".equals(dto.getRole())) {
            return toSocieteUser(dto);
        } else {
            return toAccountantUser(dto);
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "linkedCompany", ignore = true) // Géré manuellement dans le service
    SocieteUser toSocieteUser(UserRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    AccountantUser toAccountantUser(UserRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "linkedCompany", ignore = true)
    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User user);
}