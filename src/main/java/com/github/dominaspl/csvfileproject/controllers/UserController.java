package com.github.dominaspl.csvfileproject.controllers;

import com.github.dominaspl.csvfileproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/upload-file")
    private String displayRegisterForm() {

        return "upload-file";
    }

    @GetMapping("/number-of-users")
    public String displayNumberOfUsers(Model model) {

        model.addAttribute("numberOfUsers", userService.getAllUsers().size());

        return "number-of-users";
    }

    @GetMapping("/users-sorted-by-age")
    public String displaySortedUsersByAge(Model model) {
        model.addAttribute("users", userService.getSortedUsersByAge());
        return "users-sorted-by-age";
    }

    @GetMapping("/oldest-user-with-phone")
    public String displayOldestUserWithPhoneNumber(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user-with-phone";
    }

    @PostMapping("/upload-file")
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
