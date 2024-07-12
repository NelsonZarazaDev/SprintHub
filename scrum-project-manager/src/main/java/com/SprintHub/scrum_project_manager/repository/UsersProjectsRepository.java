package com.SprintHub.scrum_project_manager.repository;

import com.SprintHub.scrum_project_manager.model.UsersProjects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersProjectsRepository extends JpaRepository<UsersProjects,Integer> {
}
