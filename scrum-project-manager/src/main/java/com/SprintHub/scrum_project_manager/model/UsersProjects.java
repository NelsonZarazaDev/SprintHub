package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usersprojects")
public class UsersProjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_project")
    private int idUserProject;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "token_users_projects")
    private String tokenUsersProjects = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));
}
