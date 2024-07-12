package com.SprintHub.scrum_project_manager.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int idUser;

    @Column(name = "firstname_user")
    @NotBlank(message = "{user_notBlank.firstnameUser}")
    @Size(max = 50, message = "{user_size.firstnameUser}")
    private String firstnameUser;

    @Column(name = "last_name_user")
    @NotBlank(message = "{user_notBlank.lastNameUser}")
    @Size(max = 50, message = "{user_size.lastNameUser}")
    private String lastNameUser;

    @Column(name = "date_birth_user")
    @NotNull(message = "{user_notNull.dateBirthUser}")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "{user_past.dateBirthUser}")
    private LocalDate dateBirthUser;

    @Column(name = "email_user")
    @NotBlank(message = "{user_notBlank.emailUser}")
    @Email(message = "{user_email.emailUser}")
    private String emailUser;

    @Column(name = "password_user")
    @NotBlank(message = "{user_notBlank.passwordUser}")
    @Size(min = 8, message = "{user_size.passwordUser}")
    private String passwordUser;

    @Column(name = "token_user")
    private String tokenUser = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));
}