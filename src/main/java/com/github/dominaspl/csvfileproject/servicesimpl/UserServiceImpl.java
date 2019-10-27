package com.github.dominaspl.csvfileproject.servicesimpl;

import com.github.dominaspl.csvfileproject.converters.AgeConverter;
import com.github.dominaspl.csvfileproject.converters.UserConverter;
import com.github.dominaspl.csvfileproject.dtos.UserDTO;
import com.github.dominaspl.csvfileproject.entities.User;
import com.github.dominaspl.csvfileproject.repositories.UserRepository;
import com.github.dominaspl.csvfileproject.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUsers(String usersStr) {

        if (usersStr == null) {
            throw new IllegalArgumentException("Users must be given!");
        }

        List<String> userData = new ArrayList<>(Arrays.asList(usersStr.split("\n")));

        List<UserDTO> userDTOS = setUsersData(convertUserData(userData));

        for (UserDTO userDTO : userDTOS) {
            userRepository.save(UserConverter.convertToUser(userDTO));
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        if (allUsers == null) {
            throw new IllegalStateException("Users not found!");
        }

        return UserConverter.convertToUserDTO(allUsers);
    }

    @Override
    public List<UserDTO> getSortedUsersByAge() {

        List<User> allUsers = userRepository.findAll();

        if (allUsers == null) {
            throw new IllegalStateException("Users not found!");
        }

        List<UserDTO> users = UserConverter.convertToUserDTO(allUsers);

        users.sort((u1, u2) -> {
            return u1.getAge().compareTo(u2.getAge());
        });

        return users;
    }

    @Override
    public List<UserDTO> findOldestUserWithPhoneNumber() {

        List<User> users = userRepository.findOldestUserAndPhoneNumberIsNotNull();

        if (users == null) {
            throw new IllegalStateException("Users not found!");
        }

        return UserConverter.convertToUserDTO(users);
    }

    public LocalDate convertToLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.d");
        return LocalDate.parse(date, formatter);
    }

    public List<String> convertUserData(List<String> userData) {

        List<String> filteredData = userData.stream()
                .skip(1)
                .filter(s -> !s.isEmpty())
                .map(s -> {
                    if (s.endsWith(";")) {
                        s = s.substring(0, s.length() - 1);
                    }
                    return s;
                })
                .map(s -> s.replaceAll("\\s+", ""))
                .collect(Collectors.toList());

        return filteredData;
    }

    public List<UserDTO> setUsersData(List<String> filteredData) {

        List<UserDTO> users = new ArrayList<>();

        for (String data : filteredData) {
            String[] split = data.split(";");
            if (split.length > 2) {
                UserDTO userDTO = new UserDTO();
                userDTO.setFirstName(split[0]);
                userDTO.setLastName(split[1]);
                if (split[2].matches("\\d{4}\\.\\d{1,2}\\.\\d{1,2}")) {
                    userDTO.setBirthDate(convertToLocalDate(split[2]));
                    userDTO.setAge(AgeConverter.convertBirthToAge(userDTO.getBirthDate()));
                }
                if (split.length > 3) {
                    if (split[3].matches("\\d{9}")) {
                        userDTO.setPhoneNumber(split[3]);
                    }
                }
                users.add(userDTO);
            }
        }
        return users;
    }

}
