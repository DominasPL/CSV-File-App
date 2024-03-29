package com.github.dominaspl.csvfileproject.dtos;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Data
@RequestMapping
public class UserDTO {

    private Long userId;
//
//    @NotEmpty
//    @Size(min = 1, max = 100)
    private String firstName;

//    @NotEmpty
//    @Size(min = 1, max = 100)
    private String lastName;

//    @NotEmpty
//    @Pattern(regexp = "\\d{4}\\.\\d{1,2}\\.\\d{1,2}")
    private LocalDate birthDate;

//    @NotBlank
    private Integer age;

//    @Pattern(regexp = "\\d{9}")
    private String phoneNumber;

}
