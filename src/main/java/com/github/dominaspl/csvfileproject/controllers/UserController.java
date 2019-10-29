package com.github.dominaspl.csvfileproject.controllers;

import com.github.dominaspl.csvfileproject.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/upload-file")
    private String displayUploadFileForm() {
        return "upload-file";
    }

    @PostMapping("/upload-file")
    private String addUsersFromGivenFile(@RequestParam("file") MultipartFile file) {

        if (file == null) {
            logger.error("File has null value");
            throw new IllegalArgumentException("File must be given!");
        }

        if (file.getOriginalFilename().endsWith(".csv")) {
            userService.addUsers(file);
        } else {
            logger.error("Incorrect file given");
        }

        return "redirect:/";
    }


    @GetMapping("/number-of-users")
    public String displayNumberOfUsers(Model model) {

        model.addAttribute("numberOfUsers", userService.getAllUsers().size());
        return "number-of-users";
    }

    @GetMapping("/users-sorted-by-age")
    public String displaySortedUsersByAge(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("users", userService.getSortedUsersByAge(page));
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
        model.addAttribute("user", userService.findOldestUserWithPhoneNumber());
        return "user-with-phone";
    }

    @GetMapping("/user-data-by-last-name")
    public String displayUserNameForm() {

        return "last-name-form";
    }

    @PostMapping("/user-data-by-last-name")
    public String findUserByName(@RequestParam("last-name") String lastName, Model model) {
        model.addAttribute("user", userService.findUserByName(lastName));
        return "user-by-last-name";
    }





}
