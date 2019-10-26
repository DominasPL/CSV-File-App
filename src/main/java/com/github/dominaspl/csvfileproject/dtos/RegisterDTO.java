package com.github.dominaspl.csvfileproject.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@RequestMapping
public class RegisterDTO {

    private String firstName;

    private String lastName;

}
