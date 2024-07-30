package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * FROM users WHERE token_user = :tokenUser", nativeQuery = true)
    Users searchUser(@Param("tokenUser") String tokenUser);

    @Query(value = "SELECT * FROM users WHERE email_user = :emailUser", nativeQuery = true)
    Optional<Users> getUserByEmail(@Param("emailUser") String emailUser);

    @Query(value = "SELECT * FROM users WHERE token_user = :tokenUser", nativeQuery = true)
    Optional<Users> getUserByToken(@Param("tokenUser") String tokenUser);
}
