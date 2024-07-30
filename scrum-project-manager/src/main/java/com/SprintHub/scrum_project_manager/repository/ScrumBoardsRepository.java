package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.ScrumBoards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScrumBoardsRepository extends JpaRepository<ScrumBoards, Integer> {
    @Query(value = "SELECT uh.name_hu, uh.token_hu " +
            "FROM userstories uh " +
            "INNER JOIN scrumboards s ON uh.id_user_story=s.user_story_id", nativeQuery = true)
    List<ScrumBoardsJoin> getAllHuScrumboard();

}
