package com.github.dominaspl.csvfileproject.repositories;

import com.github.dominaspl.csvfileproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
