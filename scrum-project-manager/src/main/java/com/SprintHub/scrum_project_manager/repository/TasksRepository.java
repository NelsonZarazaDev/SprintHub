package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {

    @Query(value = "SELECT * FROM tasks WHERE token_task= :tokenTask", nativeQuery = true)
    Optional<Tasks> getTasks(@Param("tokenTask") String tokenTask);

    @Query(value = "SELECT t.*, uh.name_hu, p.name_project, CONCAT(u.firstname_user,' ',u.last_name_user) AS name " +
            "FROM tasks t " +
            "INNER JOIN userstories uh ON t.user_story_id=uh.id_user_story " +
            "INNER JOIN projects p ON uh.project_id = p.id_project " +
            "LEFT OUTER JOIN usersprojects up ON t.user_story_id=up.id_user_project " +
            "INNER JOIN users u ON u.id_user=up.user_id " +
            "WHERE t.token_task= :tokenTask", nativeQuery = true)
    List<TaskJoin> getTasksByTokenTask(@Param("tokenTask") String tokenTask);

    @Query(value = "SELECT t.*, uh.name_hu, p.name_project, CONCAT(u.firstname_user,' ',u.last_name_user) AS name " +
            "FROM tasks t " +
            "INNER JOIN userstories uh ON t.user_story_id=uh.id_user_story " +
            "INNER JOIN projects p ON uh.project_id = p.id_project " +
            "LEFT OUTER JOIN usersprojects up ON t.user_story_id=up.id_user_project " +
            "INNER JOIN users u ON u.id_user=up.user_id " +
            "WHERE t.state_task='t'", nativeQuery = true)
    List<TaskJoin> getTasks();
}
