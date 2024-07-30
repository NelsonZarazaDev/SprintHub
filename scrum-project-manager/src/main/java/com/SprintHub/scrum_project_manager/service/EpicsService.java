package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Epics;
import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.UserStories;
import com.SprintHub.scrum_project_manager.repository.EpicsRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class EpicsService {
    @Autowired
    private EpicsRepository epicsRepository;

    @Autowired
    private ProjectsService projectsService;

    public Epics createEpic(Epics epics, String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        epics.setProjectId(projects.getIdProject());
        return epicsRepository.save(epics);
    }

    public List<Epics> getEpicsByProjectIdByToken(String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        return (List<Epics>) epicsRepository.getEpicsByProjectIdByToken(projects.getIdProject());
    }

    public Epics getEpicByTokenEpic(String tokenEpic) {
        Optional<Epics> epics = epicsRepository.getEpicByTokenEpic(tokenEpic);
        if (tokenEpic.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        return epics.get();
    }

    public Epics updateEpicByTokenEpic(Epics epics, String tokenEpic) {
        Optional<Epics> epicsbd = epicsRepository.getEpicByTokenEpic(tokenEpic);
        if (epicsbd.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        epicsbd.get().setNameEpic(epics.getNameEpic());
        epicsbd.get().setDescriptionEpic(epics.getDescriptionEpic());
        return epicsRepository.save(epicsbd.get());
    }

    public boolean deleteEpic(String tokenEpic) {
        Optional<Epics> epics = epicsRepository.getEpicByTokenEpic(tokenEpic);
        if (epics.isEmpty()){
            throw new NotFoundException(Constants.EPIC_BY_TOKEN_EPIC_NOT_FOUND.getMessage());
        }
        epicsRepository.delete(epics.get());
        return true;
    }
}
