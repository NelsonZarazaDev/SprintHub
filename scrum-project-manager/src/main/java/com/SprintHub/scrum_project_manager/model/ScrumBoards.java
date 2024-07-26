package com.SprintHub.scrum_project_manager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scrumboards")
public class ScrumBoards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scrumboard")
    private int idScrumboard;

    @Column(name = "start_date_scrumboards")
    @FutureOrPresent(message = "{scrum_board.project_futureOrPresent.startDateScrumboard}")
    private Date startDateScrumboard;

    @Column(name = "end_date_scrumboards")
    @FutureOrPresent(message = "{scrum_board.project_futureOrPresent.endDateScrumboard}")
    private Date endDateScrumboard;

    @Column(name = "spring_number_scrumboards")
    private String springNumberScrumboard;

    @Column(name = "state_scrumboards")
    private Boolean stateScrumboards = TRUE;

    @Column(name = "token_scrumboards")
    private String tokenScrumboard = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));

    @Column(name = "user_story_id")
    private int userStoryId;

    @Transient
    private String[] arrayTokenUserHistory;

    @Column(name = "project_id")
    private int projectId;
}
