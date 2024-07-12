package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.model.UsersProjects;
import com.SprintHub.scrum_project_manager.repository.ProjectsRepository;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsRepository;
import com.SprintHub.scrum_project_manager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersProjectsService {
    @Autowired
    private UsersProjectsRepository usersProjectsRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private ProjectsService projectsService;

    public UsersProjects createUsersProjects(UsersProjects usersProjects, String emailUser, String tokenProject) {
        Users users = usersService.getUserByEmail(emailUser);
        usersProjects.setUserId(users.getIdUser());

        Projects projects = projectsService.getProjectByToken(tokenProject);
        usersProjects.setProjectId(projects.getIdProject());

        return usersProjectsRepository.save(usersProjects);
    }

}
