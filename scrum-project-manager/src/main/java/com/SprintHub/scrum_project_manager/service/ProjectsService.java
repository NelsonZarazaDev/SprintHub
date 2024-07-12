package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.repository.ProjectsRepository;
import com.SprintHub.scrum_project_manager.repository.UsersRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectsService {
    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersService usersService;

    /*Create project*/
    public Projects createProject(Projects projects, String email) {
        Users user = usersService.getUserByEmail(email);
        projects.setMasterScrumId(user.getIdUser());
        return projectsRepository.save(projects);
    }

    /*Read project*/
    public Projects getProjectByToken(String tokenProject) {
        Optional<Projects> projects = projectsRepository.getProjectByToken(tokenProject);
        if (projects.isEmpty()){
            throw new NotFoundException(Constants.PROJECT_NOT_FOUND.getMessage());
        }
        return projects.get();
    }

    /*Update project*/
    public Projects updateProject(Projects projects) {
        Optional<Projects> projectBd = projectsRepository.getProjectByToken(projects.getTokenProject());
        if (projectBd.isEmpty()){
            throw new NotFoundException(Constants.PROJECT_NOT_FOUND.getMessage());
        }
        projectBd.get().setNameProject(projects.getNameProject());
        projectBd.get().setDescriptionProject(projects.getDescriptionProject());
        projectBd.get().setEndDateProject(projects.getEndDateProject());
        return projectsRepository.save(projectBd.get());
    }

    /*Delete project*/
    public boolean deleteProjectsByToken(String tokenProject) {
        Optional<Projects> projects = projectsRepository.getProjectByToken(tokenProject);
        if(projects.isEmpty()){
            return false;
        }
        projectsRepository.deleteProjectsByToken(tokenProject);
        return true;
    }

    public List<Projects> getProjectMasterScrumById(int masterScrumId) {
        return (List<Projects>) projectsRepository.getProjectMasterScrumById(masterScrumId);
    }


}
