package com.github.dominaspl.csvfileproject.services;

import com.github.dominaspl.csvfileproject.dtos.UserDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    void addUsers(String users);

    List<UserDTO> getAllUsers();

    Page<UserDTO> getSortedUsersByAge(int page);

    UserDTO findOldestUserWithPhoneNumber();

    void deleteUser(Long userId);

    void deleteAllUsers();

    UserDTO findUserByName(String userName);
}
