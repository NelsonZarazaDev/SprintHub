package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.model.UsersProjects;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsByTokenUsersJoin;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsJoin;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    /*Read project by token*/
    public List<UsersProjectsJoin> getUsersProjects(String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        return usersProjectsRepository.getUsersProjects(projects.getIdProject());
    }

    /*Read projects*/
    public List<UsersProjectsByTokenUsersJoin> getProjectsUsersProjects(String tokenUser) {
        return usersProjectsRepository.getProjectsByTokenUsers(tokenUser);
    }

    /*UPDATE PROJECT USERS*/
    public UsersProjects updateProjectUser(UsersProjects usersProjects, String tokenUserProject) {
        Optional<UsersProjects> usersProjectsBd = usersProjectsRepository.getUserProjectByToken(tokenUserProject);
        if (usersProjectsBd.isEmpty()) {
            throw new NotFoundException(Constants.USERS_PROJECT_NOT_FOUNT.getMessage());
        }
        usersProjectsBd.get().setRoleId(usersProjects.getRoleId());
        return usersProjectsRepository.save(usersProjectsBd.get());
    }

    /*DELETE PROJECT USER*/
    public Boolean deleteUsersProjects(String tokenUsersProjects) {
        Optional<UsersProjects> usersProjects = usersProjectsRepository.getUserProjectByToken(tokenUsersProjects);
        if (usersProjects.isEmpty()) {
            throw new NotFoundException(Constants.USERS_PROJECT_NOT_FOUNT.getMessage());
        }
        usersProjectsRepository.delete(usersProjects.get());
        return true;
    }
}
