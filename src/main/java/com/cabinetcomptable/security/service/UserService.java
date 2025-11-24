package com.cabinetcomptable.security.service;

import com.cabinetcomptable.security.entity.User;
import com.cabinetcomptable.security.exception.UserNotFoundException;
import com.cabinetcomptable.security.dao.UserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO userDAO;

    public User findByEmail(String email) {
        return userDAO.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}