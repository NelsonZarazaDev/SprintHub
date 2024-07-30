package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "epics")
public class Epics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_epic")
    private int idEpic;

    @Column(name = "name_epic")
    @NotNull(message = "{epics.notNull.nameEpic}")
    @Size(max = 500, message = "{epics.size.nameEpic}")
    private String nameEpic;

    @Column(name = "description_epic")
    @Size(max = 2000, message = "{epics.size.descriptionEpic}")
    private String descriptionEpic;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "token_epic")
    private String tokenEpic = (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));

}
