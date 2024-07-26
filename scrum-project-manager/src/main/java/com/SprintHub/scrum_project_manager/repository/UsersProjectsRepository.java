package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.model.UsersProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;

public interface UsersProjectsRepository extends JpaRepository<UsersProjects, Integer> {

    @Query(value = "SELECT p.name_project, CONCAT(sm.firstname_user, ' ', sm.last_name_user) AS scrum_master_name, p.token_project " +
            "FROM users u " +
            "INNER JOIN usersprojects up ON u.id_user = up.user_id " +
            "INNER JOIN projects p ON up.project_id = p.id_project " +
            "LEFT JOIN users sm ON p.master_scrum_id = sm.id_user " +
            "WHERE u.token_user = :tokenUser",
            nativeQuery = true)
    List<UsersProjectsByTokenUsersJoin> getProjectsByTokenUsers(@Param("tokenUser") String tokenUser);

    @Query(value = "SELECT CONCAT(u.firstname_user,' ',u.last_name_user) AS name_user, u.email_user , r.name_role, up.token_users_projects " +
            "FROM usersprojects up " +
            "INNER JOIN users u ON up.user_id = u.id_user " +
            "RIGHT JOIN roles r ON up.role_id=r.id_role " +
            "WHERE up.project_id = :projectId ", nativeQuery = true)
    List<UsersProjectsJoin> getUsersProjects(@Param("projectId") int projectId);

    @Query(value = "SELECT *  FROM usersprojects WHERE token_users_projects=:tokenUserProject", nativeQuery = true)
    Optional<UsersProjects> getUserProjectByToken(@Param("tokenUserProject") String tokenUserProject);

    List<UsersProjects> findByUserId(int user_id);
}
