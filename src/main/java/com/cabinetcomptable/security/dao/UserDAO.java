package com.cabinetcomptable.security.dao;

import com.cabinetcomptable.security.entity.User;
import org.mapstruct.control.MappingControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    List<User> findUserByPassword(String password);

    Optional<User> findUsersByEmailAndPassword(String email, String password);

    Optional<User> findUsersByRoleAndEmailAndPassword(String role, String email, String password);

    boolean existsByEmail(String email);
}
