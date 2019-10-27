package com.github.dominaspl.csvfileproject.controllers;

import com.github.dominaspl.csvfileproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/users-sorted-by-age/delete/{id}")
    public String deleteUser(@PathVariable("id") Long userId) {

        userService.deleteUser(userId);
        return "redirect:/users/users-sorted-by-age";
    }

    @GetMapping("/users-sorted-by-age/delete-users")
    public String deleteUser() {

        userService.deleteAllUsers();
        return "redirect:/";
    }

    @GetMapping("/oldest-user-with-phone")
    public String displayOldestUserWithPhoneNumber(Model model) {
        model.addAttribute("users", userService.findOldestUserWithPhoneNumber());
        return "user-with-phone";
    }

    @GetMapping("/user-data-by-name")
    public String displayUserNameForm() {

        return "last-name-form";
    }

    @PostMapping("/user-data-by-name")
    public String findUserByName(@RequestParam("last-name") String lastName, Model model) {
        model.addAttribute("user", userService.findUserByName(lastName));
        return "user-by-last-name";
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
