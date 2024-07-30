package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "userstories")
public class UserStories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_story")
    private int idUserStory;

    @Column(name = "name_hu")
    @NotBlank(message = "{user_stories.notBlank.nameHu}")
    @Size(min = 500, message = "{user_stories.size.nameHu}")
    private String nameHu;

    @Column(name = "description_hu")
    @Size(min = 2000, message = "{user_stories.size.descriptionHu}")
    private String descriptionHu;

    @Column(name = "as_hu")
    @NotBlank(message = "{user_stories.notBlank.asHu}")
    private String asHu;

    @Column(name = "want_hu")
    @NotBlank(message = "{user_stories.notBlank.wantHu}")
    private String wantHu;

    @Column(name = "to_hu")
    @NotBlank(message = "{user_stories.notBlank.toHu}")
    private String toHu;

    @Column(name = "priority_hu")
    private String priorityHu;

    @Column(name = "estimation_hu")
    private String estimationHu;

    @Column(name = "criteria_acceptance_hu")
    private String criteriaAcceptanceHu;

    @Column(name = "state_hu")
    private Boolean stateHu = TRUE;

    @Column(name = "token_hu")
    private String tokenHu = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 200) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 50));

    @Column(name = "epic_id")
    private int epicId;

    @Column(name = "module_id")
    private int moduleId;

    @Column(name = "project_id")
    private int projectId;
}
