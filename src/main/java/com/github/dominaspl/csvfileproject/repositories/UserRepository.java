package com.github.dominaspl.csvfileproject.repositories;

import com.github.dominaspl.csvfileproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users\n" +
            "WHERE birth_date = \n" +
            "(SELECT MIN(birth_date) FROM users)\n" +
            "AND phone_number IS NOT NULL", nativeQuery = true)
    List<User> findOldestUserAndPhoneNumberIsNotNull();

    Optional<User> findByLastName(String lastName);
}
