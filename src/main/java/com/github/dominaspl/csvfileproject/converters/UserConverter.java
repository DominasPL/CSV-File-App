package com.github.dominaspl.csvfileproject.converters;

import com.github.dominaspl.csvfileproject.dtos.UserDTO;
import com.github.dominaspl.csvfileproject.entities.User;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {
    public static User convertToUser(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    public static List<UserDTO> convertToUserDTO(List<User> allUsers) {

        List<UserDTO> users = new ArrayList<>();

        for (User user : allUsers) {
            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setBirthDate(user.getBirthDate());
            userDTO.setAge(AgeConverter.convertBirthToAge(user.getBirthDate()));
            userDTO.setPhoneNumber(user.getPhoneNumber());
            users.add(userDTO);
        }

        return users;
    }
}
