package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Epics;
import com.SprintHub.scrum_project_manager.model.Modules;
import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.UserStories;
import com.SprintHub.scrum_project_manager.repository.UserStoriesRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Service
public class UserStoriesService {
    @Autowired
    private UserStoriesRepository userStoriesRepository;

    @Autowired
    private EpicsService epicsService;

    @Autowired
    private ModulesService modulesService;

    @Autowired
    private ProjectsService projectsService;

    public UserStories createUserStories(UserStories userStories, String tokenEpic, String tokenModule, String tokenProject) {
        Epics epics = epicsService.getEpicByTokenEpic(tokenEpic);
        Modules modules = modulesService.getModulesByTokenModule(tokenModule);
        Projects projects = projectsService.getProjectByToken(tokenProject);
        userStories.setEpicId(epics.getIdEpic());
        userStories.setModuleId(modules.getIdModule());
        userStories.setProjectId(projects.getIdProject());
        return userStoriesRepository.save(userStories);
    }

    public List<UserStories> getUserStoriesByProjectId(String tokenProject) {
        Projects projects = projectsService.getProjectByToken(tokenProject);
        return (List<UserStories>) userStoriesRepository.getUserStoriesByProjectId(projects.getIdProject());
    }

    public UserStories getUserStoriesByTokenHu(String tokenHu) {
        Optional<UserStories> userStories = userStoriesRepository.getUserStoriesByTokenHu(tokenHu);
        if (userStories.isEmpty()){
            throw new NotFoundException(Constants.USER_STORY_TOKEN_HU_BY_TOKEN_NOT_FOUND.getMessage());
        }
        return userStories.get();
    }

    public UserStories updateUserStories(UserStories userStories, String tokenHu) {
        Optional<UserStories> userStoriesBd = userStoriesRepository.getUserStoriesByTokenHu(tokenHu);
        if (userStoriesBd.isEmpty()){
            throw new NotFoundException(Constants.USER_STORY_TOKEN_HU_BY_TOKEN_NOT_FOUND.getMessage());
        }
        userStoriesBd.get().setNameHu(userStories.getNameHu());
        userStoriesBd.get().setDescriptionHu(userStories.getDescriptionHu());
        userStoriesBd.get().setAsHu(userStories.getAsHu());
        userStoriesBd.get().setWantHu(userStories.getWantHu());
        userStoriesBd.get().setToHu(userStories.getToHu());
        userStoriesBd.get().setPriorityHu(userStories.getPriorityHu());
        userStoriesBd.get().setEstimationHu(userStories.getEstimationHu());
        userStoriesBd.get().setCriteriaAcceptanceHu(userStories.getCriteriaAcceptanceHu());
        return userStoriesRepository.save(userStoriesBd.get());
    }

    public boolean deleteUserStories(String tokenHu) {
        Optional<UserStories> userStories = userStoriesRepository.getUserStoriesByTokenHu(tokenHu);
        if (userStories.isEmpty()){
            throw new NotFoundException(Constants.USER_STORY_TOKEN_HU_BY_TOKEN_NOT_FOUND.getMessage());
        }
        userStoriesRepository.delete(userStories.get());
        return true;
    }
}
