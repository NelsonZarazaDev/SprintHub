package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Epics;
import com.SprintHub.scrum_project_manager.model.Modules;
import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.UserStories;
import com.SprintHub.scrum_project_manager.repository.ModulesRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModulesService {
    @Autowired
    private ModulesRepository modulesRepository;

    @Autowired
    private EpicsService epicsService;

    @Autowired
    private ProjectsService projectsService;

    public Modules createModule(Modules modules, String tokenEpic, String tokenProject) {
        Epics epics = epicsService.getEpicByTokenEpic(tokenEpic);
        Projects projects = projectsService.getProjectByToken(tokenProject);
        modules.setEpicId(epics.getIdEpic());
        modules.setProjectId(projects.getIdProject());
        return modulesRepository.save(modules);
    }

    public List<Modules> getModulesByPojectIdToken(String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        return (List<Modules>) modulesRepository.getModulesByPojectIdToken(projects.getIdProject());
    }

    public Modules getModulesByTokenModule(String tokenModule) {
        Optional<Modules> modules = modulesRepository.getModulesByTokenModule(tokenModule);
        if (modules.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        return modules.get();
    }

    public Modules updateModule(Modules modules, String tokenEpic) {
        Optional<Modules> modulesBd = modulesRepository.getModulesByTokenModule(tokenEpic);
        if (modulesBd.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        modulesBd.get().setNameModule(modules.getNameModule());
        modulesBd.get().setDescriptionModule(modules.getDescriptionModule());
        return modulesRepository.save(modulesBd.get());
    }

    public boolean deleteModule(String tokenModule) {
        Optional<Modules> modules = modulesRepository.getModulesByTokenModule(tokenModule);
        if (modules.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        modulesRepository.delete(modules.get());
        return true;
    }
}
