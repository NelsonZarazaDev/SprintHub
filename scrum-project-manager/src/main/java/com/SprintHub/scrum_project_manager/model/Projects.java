package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private int idProject;

    @Column(name = "name_project")
    @NotBlank(message = "{project_notBlank.nameProject}")
    @Size(max = 500, message = "{project_size.nameProject}")
    private String nameProject;

    @Column(name = "description_project")
    @Size(max = 1000, message = "{project_size.descriptionProject}")
    private String descriptionProject;

    @Column(name = "master_scrum_id")
    @NotNull(message = "{project_notNull.nameProject}")
    private int masterScrumId; //MODIFICAR

    @Column(name = "start_date_project")
    @NotNull(message = "{project_notNull.startDateProject}")
    @FutureOrPresent(message = "{project_futureOrPresent.startDateProject}")
    private LocalDate startDateProject;

    @Column(name = "end_date_project")
    @NotNull(message = "{project_notNull.endDateProject}")
    @FutureOrPresent(message = "{project_futureOrPresent.endDateProject}")
    private LocalDate endDateProject;

    @Column(name = "token_project")
    private String tokenProject = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));
}
