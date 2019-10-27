package com.github.dominaspl.csvfileproject.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@RequestMapping
public class UserDTO {

    private Long userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private Integer age;

    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

}
