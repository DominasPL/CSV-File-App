package com.github.dominaspl.csvfileproject.services;

import com.github.dominaspl.csvfileproject.dtos.UserDTO;

import java.util.List;

public interface UserService {

    void addUsers(String users);

    List<UserDTO> getAllUsers();
}
