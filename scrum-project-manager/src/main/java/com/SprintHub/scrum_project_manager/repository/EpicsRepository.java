package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Epics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EpicsRepository extends JpaRepository<Epics, Integer> {
    @Query(value = "SELECT * FROM epics WHERE project_id= :projectTokenId", nativeQuery = true)
    List<Epics> getEpicsByProjectIdByToken(@Param("projectTokenId") int projectTokenId);

    @Query(value = "SELECT * FROM epics WHERE token_epic= :tokenEpic", nativeQuery = true)
    Optional<Epics> getEpicByTokenEpic(@Param("tokenEpic") String tokenEpic);
}
