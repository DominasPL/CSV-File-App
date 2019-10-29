package com.github.dominaspl.csvfileproject.servicesimpl;

import com.github.dominaspl.csvfileproject.converters.AgeConverter;
import com.github.dominaspl.csvfileproject.converters.UserConverter;
import com.github.dominaspl.csvfileproject.dtos.UserDTO;
import com.github.dominaspl.csvfileproject.entities.User;
import com.github.dominaspl.csvfileproject.repositories.UserRepository;
import com.github.dominaspl.csvfileproject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUsers(MultipartFile file) {

        if (file == null) {
            logger.error("File has null value");
            throw new IllegalArgumentException("File must be given!");
        }
        setUsersDataAndSave(convertUserData(convertFileToString(file)));
        logger.info("Users have been successfully added");
    }

    @Override
    public List<UserDTO> getAllUsers() {

        List<User> allUsers = userRepository.findAll();

        if (allUsers == null) {
            logger.error("Users not found");
            throw new IllegalStateException("Users not found!");
        }

        return UserConverter.convertToUserDTOList(allUsers);
    }

    @Override
    public Page<UserDTO> getSortedUsersByAge(int page) {

        Page<User> users = userRepository.findAllAndOrderedByBirthDate(PageRequest.of(page, 5));

        if (users == null) {
            logger.error("Users not found");
            throw new IllegalStateException("Users not found!");
        }

        return users.map(user -> UserConverter.convertToUserDTO(user));
    }

    @Override
    public UserDTO findOldestUserWithPhoneNumber() {

        User user = userRepository.findOldestUserAndPhoneNumberIsNotNull();

        if (user == null) {
            logger.error("User not found");
            throw new IllegalStateException("User not found!");
        }

        return UserConverter.convertToUserDTO(user);
    }

    @Override
    public void deleteUser(Long userId) {

        if (userId == null) {
            logger.error("Id has null value");
            throw new IllegalArgumentException("Id must be given!");
        }

        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> {
            logger.error("User not found");
            return new IllegalStateException("User not found");
        });

        logger.info("Deleting user");
        userRepository.delete(user);
    }

    @Override
    public void deleteAllUsers() {

        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            logger.info("Deleting all users");
            users
                .forEach(user -> userRepository.delete(user));
        }

    }

    @Override
    public UserDTO findUserByName(String lastName) {

        if (lastName == null) {
            logger.error("Last name is not given");
            throw new IllegalArgumentException("Last name must be given!");
        }

        Optional<User> optionalUser = userRepository.findByLastName(lastName);
        User user = optionalUser.orElseThrow(() ->  {
            logger.error("User not found");
            return new IllegalStateException("User not found");
        });

        return UserConverter.convertToUserDTO(user);
    }

    private String convertFileToString(MultipartFile file) {

        String data = "";

        try {
            data = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    private List<String> convertUserData(String data) {

        List<String> userData = new ArrayList<>(Arrays.asList(data.split("\n")));

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

    private void setUsersDataAndSave(List<String> filteredData) {

        for (String data : filteredData) {
            String[] split = data.split(";");
            if (split.length > 2 && split[2].matches("\\d{4}\\.\\d{1,2}\\.\\d{1,2}")) {
                UserDTO userDTO = new UserDTO();
                if (!split[0].isEmpty() && !split[1].isEmpty()) {
                    userDTO.setFirstName(split[0]);
                    userDTO.setLastName(split[1]);
                    userDTO.setBirthDate(convertToLocalDate(split[2]));
                    userDTO.setAge(AgeConverter.convertBirthToAge(userDTO.getBirthDate()));
                    if (split.length > 3 && split[3].matches("\\d{9}") && checkIsPhoneNumberAvailable(split[3])) {
                        userDTO.setPhoneNumber(split[3]);
                    }
                    userRepository.save(UserConverter.convertToUser(userDTO));
                    logger.info("Adding new user");
                }
            }
        }
    }

    private LocalDate convertToLocalDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.M.d");
        return LocalDate.parse(date, formatter);
    }

    private boolean checkIsPhoneNumberAvailable(String phoneNumber) {

        User user = userRepository.findByPhoneNumber(phoneNumber);

        return user == null;
    }

}
