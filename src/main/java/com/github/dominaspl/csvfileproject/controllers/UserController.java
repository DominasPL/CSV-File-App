package com.github.dominaspl.csvfileproject.controllers;

import com.github.dominaspl.csvfileproject.dtos.RegisterDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    private String displayRegisterForm(Model model) {

        model.addAttribute("form", new RegisterDTO());
        return "register";
    }


}
