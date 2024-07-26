package com.SprintHub.scrum_project_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "modules")
public class Modules {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_module")
    private int idModule;

    @Column(name = "name_module")
    @NotBlank(message = "{modules.notBlank.nameModule}")
    @Size(max = 500, message = "{modules.size.nameModule}")
    private String nameModule;

    @Column(name = "description_module")
    @Size(max = 2000, message = "{modules.size.descriptionModule}")
    private String descriptionModule;

    @Column(name = "epic_id")
    private int epicId;

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "token_module")
    private String tokenModule= (Integer.toString((int) System.nanoTime()) + "" +
            (Math.random() * 100) + UUID.randomUUID() +
            (Math.random() * 100) + UUID.randomUUID() +
            System.nanoTime() + "" +
            (Math.random() * 100));

}
