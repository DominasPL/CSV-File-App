package com.github.dominaspl.csvfileproject.repositories;

import com.github.dominaspl.csvfileproject.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM users\n" +
            "WHERE phone_number IS NOT NULL\n" +
            "ORDER BY birth_date\n" +
            "LIMIT 1;", nativeQuery = true)
    User findOldestUserAndPhoneNumberIsNotNull();

    Optional<User> findByLastName(String lastName);

    @Query(value = "SELECT * FROM users\n" +
            "ORDER BY birth_date DESC",nativeQuery = true)
    Page<User> findAllAndOrderedByBirthDate(PageRequest of);

    User findByPhoneNumber(String phoneNumber);
}
