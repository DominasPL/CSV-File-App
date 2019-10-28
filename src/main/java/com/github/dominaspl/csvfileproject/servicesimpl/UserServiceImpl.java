package com.github.dominaspl.csvfileproject.servicesimpl;

import com.github.dominaspl.csvfileproject.converters.AgeConverter;
import com.github.dominaspl.csvfileproject.converters.UserConverter;
import com.github.dominaspl.csvfileproject.dtos.UserDTO;
import com.github.dominaspl.csvfileproject.entities.User;
import com.github.dominaspl.csvfileproject.repositories.UserRepository;
import com.github.dominaspl.csvfileproject.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

        return UserConverter.convertToUserDTOList(allUsers);
    }

    @Override
    public Page<UserDTO> getSortedUsersByAge(int page) {

        Page<User> users = userRepository.findAllAndOrderedByBirthDate(PageRequest.of(page, 5));

        if (users == null) {
            throw new IllegalStateException("Users not found!");
        }

        return users.map(user -> UserConverter.convertToUserDTO(user));
    }

    @Override
    public UserDTO findOldestUserWithPhoneNumber() {

        User user = userRepository.findOldestUserAndPhoneNumberIsNotNull();

        if (user == null) {
            throw new IllegalStateException("User not found!");
        }

        return UserConverter.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {

        if (userId == null) {
            throw new IllegalArgumentException("Id must be given!");
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("User not found"));

        userRepository.delete(user);
    }

    @Override
    public void deleteAllUsers() {

        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            users
                .forEach(user -> userRepository.delete(user));
        }

    }

    @Override
    public UserDTO findUserByName(String lastName) {

        if (lastName == null) {
            throw new IllegalArgumentException("Last name must be given!");
        }

        Optional<User> optionalUser = userRepository.findByLastName(lastName);
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("User not found"));

        return UserConverter.convertToUserDTO(user);
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
            if (split.length > 2 && split[2].matches("\\d{4}\\.\\d{1,2}\\.\\d{1,2}")) {
                UserDTO userDTO = new UserDTO();
                userDTO.setFirstName(split[0]);
                userDTO.setLastName(split[1]);
                userDTO.setBirthDate(convertToLocalDate(split[2]));
                userDTO.setAge(AgeConverter.convertBirthToAge(userDTO.getBirthDate()));
                if (split.length > 3 && split[3].matches("\\d{9}")) {
                    userDTO.setPhoneNumber(split[3]);
                }

                users.add(userDTO);
            }
        }
        return users;
    }


}
