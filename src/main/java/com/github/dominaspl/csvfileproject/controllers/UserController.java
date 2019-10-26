package com.github.dominaspl.csvfileproject.controllers;

import com.github.dominaspl.csvfileproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    private String displayRegisterForm() {

        return "upload-file";
    }

    @PostMapping
    private String addUsersFromGivenFile(@RequestParam("file") MultipartFile file) {

        String data = convertFileToString(file);

        if(data.isEmpty()) {
            throw new IllegalStateException("Incorrect data given!");
        }

        userService.addUsers(data);

        return "redirect:/";
    }

    public String convertFileToString(MultipartFile file) {

        String data = "";

        try {
            data = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
