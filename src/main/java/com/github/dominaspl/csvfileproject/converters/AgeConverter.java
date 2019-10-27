package com.github.dominaspl.csvfileproject.converters;

import java.time.LocalDate;

public class AgeConverter {


    public static Integer convertBirthToAge(LocalDate birthDate) {

        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
