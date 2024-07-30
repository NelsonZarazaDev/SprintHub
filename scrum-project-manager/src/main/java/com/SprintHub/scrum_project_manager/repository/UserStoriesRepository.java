package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.UserStories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserStoriesRepository extends JpaRepository<UserStories, Integer> {
    @Query(value = "SELECT * FROM userstories WHERE project_id= :projectId", nativeQuery = true)
    List<UserStories> getUserStoriesByProjectId(@Param("projectId") int projectId);

    @Query(value = "SELECT * FROM userstories WHERE token_hu= :tokenHu", nativeQuery = true)
    Optional<UserStories> getUserStoriesByTokenHu(@Param("tokenHu") String tokenHu);
}
