package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private int idTask;

    @Column(name = "name_task")
    @NotBlank(message = "{task.notblank.nameTask}")
    @Size(max = 500, message = "{task.size.nameTask}")
    private String nameTask;

    @Column(name = "description_task")
    @Size(max = 2000, message = "{task.size.descriptionTask}")
    private String descriptionTask;

    @Column(name = "estimated_time_task")
    @Size(max = 2, message = "{task.size.estimatedTimeTask}")
    private String estimatedTimeTask;

    @Column(name="time_task")
    @NotBlank(message = "{task.notblank.timeTask}")
    @Size(max = 2, message = "{task.size.timeTask}")
    private String timeTask;

    @Column(name = "priority_task")
    @NotBlank(message = "{task.notblank.priorityTask}")
    private String priorityTask;

    @Column(name = "task_task")
    private Boolean taskTask = TRUE;

    @Column(name = "in_progress_task")
    private Boolean inProgressTask = FALSE;

    @Column(name = "finished_task")
    private Boolean finishedTask = FALSE;

    @Column(name = "state_task")
    private Boolean stateTask = TRUE;

    @Column(name = "token_task")
    private String tokenTask = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));

    @Column(name = "responsible_user_project_id")
    private int responsibleUserProjectId;

    @Column(name = "user_story_id")
    private int userStoryId;

    @Column(name = "module_id")
    private int moduleId;

    @Column(name = "epic_id")
    private int epicId;

    @Column(name = "project_id")
    private int projectId;
}
