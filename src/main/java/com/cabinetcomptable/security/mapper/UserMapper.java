package com.cabinetcomptable.security.mapper;

import com.cabinetcomptable.security.dto.user.UserRequestDTO;
import com.cabinetcomptable.security.dto.user.UserResponseDTO;
import com.cabinetcomptable.security.dto.user.UserSimpleDTO;
import com.cabinetcomptable.security.entity.User;
import com.cabinetcomptable.security.entity.SocieteUser;
import com.cabinetcomptable.security.entity.AccountantUser;
import com.cabinetcomptable.security.entity.enums.UserRole;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface UserMapper {

    default UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole());
        responseDTO.setFullName(user.getFullName());
        responseDTO.setStatus(user.getStatus());
        responseDTO.setCreationDate(user.getCreationDate());

        if (user instanceof SocieteUser) {
            SocieteUser societeUser = (SocieteUser) user;
            if (societeUser.getLinkedCompany() != null) {
                CompanyMapper companyMapper = org.mapstruct.factory.Mappers.getMapper(CompanyMapper.class);
                responseDTO.setCompany(companyMapper.toSimpleDTO(societeUser.getLinkedCompany()));
            }
        }

        return responseDTO;
    }

    UserSimpleDTO toSimpleDTO(User user);

    List<UserResponseDTO> toResponseDTOList(List<User> users);

    List<UserSimpleDTO> toSimpleDTOList(List<User> users);

    default User toEntity(UserRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        if (UserRole.SOCIETE.equals(dto.getRole())) {
            return toSocieteUser(dto);
        } else if (UserRole.ACCOUNTANT.equals(dto.getRole())) {
            return toAccountantUser(dto);
        } else {
            throw new IllegalArgumentException("Role non supporté: " + dto.getRole());
        }
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "linkedCompany", ignore = true)
    SocieteUser toSocieteUser(UserRequestDTO dto);

    // Mapping pour AccountantUser
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    AccountantUser toAccountantUser(UserRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "role", ignore = true)
    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "linkedCompany", ignore = true)
    void updateSocieteUserFromDTO(UserRequestDTO dto, @MappingTarget SocieteUser user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateAccountantUserFromDTO(UserRequestDTO dto, @MappingTarget AccountantUser user);
}