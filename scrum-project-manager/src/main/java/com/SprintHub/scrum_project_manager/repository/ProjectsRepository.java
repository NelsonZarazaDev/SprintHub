package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectsRepository extends JpaRepository<Projects, Integer> {
    @Query(value = "SELECT * FROM projects WHERE master_scrum_id = :masterScrumId", nativeQuery = true)
    List<Projects> getProjectMasterScrumById(@Param("masterScrumId") int masterScrumId);

    @Query(value = "SELECT * FROM projects WHERE token_project = :tokenProject", nativeQuery = true)
    Optional<Projects> getProjectByToken(@Param("tokenProject") String tokenProject);
}
