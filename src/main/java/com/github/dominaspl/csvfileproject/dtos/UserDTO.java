package com.github.dominaspl.csvfileproject.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@RequestMapping
public class UserDTO {

    private Long userId;

    @NotBlank
    @Size(min = 1, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 1, max = 100)
    private String lastName;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private Integer age;

    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

}
