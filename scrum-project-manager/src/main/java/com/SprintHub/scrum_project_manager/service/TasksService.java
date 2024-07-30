package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.*;
import com.SprintHub.scrum_project_manager.repository.TaskJoin;
import com.SprintHub.scrum_project_manager.repository.TasksRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TasksService {
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private EpicsService epicsService;
    @Autowired
    private ModulesService modulesService;
    @Autowired
    private UserStoriesService userStoriesService;
    @Autowired
    private ProjectsService projectsService;

    public Tasks createTask(Tasks tasks, String tokenHU, String tokenModule, String tokenEpic, String tokenProject) {
        Epics epics = epicsService.getEpicByTokenEpic(tokenEpic);
        Modules modules = modulesService.getModulesByTokenModule(tokenModule);
        UserStories userStories = userStoriesService.getUserStoriesByTokenHu(tokenHU);
        Projects projects = projectsService.getProjectByToken(tokenProject);
        tasks.setUserStoryId(userStories.getIdUserStory());
        tasks.setModuleId(modules.getIdModule());
        tasks.setEpicId(epics.getIdEpic());
        tasks.setProjectId(projects.getIdProject());
        return tasksRepository.save(tasks);
    }

    public Tasks updateTask(Tasks tasks, String tokenTask) {
        Optional<Tasks> taskBd = tasksRepository.getTasks(tokenTask);
        if (taskBd.isEmpty()){
            throw new NotFoundException(Constants.TASK_BY_TOKEN_TASK_NOT_FOUND.getMessage());
        }
        taskBd.get().setNameTask(tasks.getNameTask());
        taskBd.get().setDescriptionTask(tasks.getDescriptionTask());
        taskBd.get().setEstimatedTimeTask(tasks.getEstimatedTimeTask());
        return tasksRepository.save(taskBd.get());
    }

    public Tasks updateTimeTask(Tasks tasks, String tokenTask) {
        Optional<Tasks> taskBd = tasksRepository.getTasks(tokenTask);
        if (taskBd.isEmpty()){
            throw new NotFoundException(Constants.TASK_BY_TOKEN_TASK_NOT_FOUND.getMessage());
        }
        taskBd.get().setTimeTask(tasks.getTimeTask());
        return tasksRepository.save(taskBd.get());
    }

    public Tasks updateProgressTask(Tasks tasks, String tokenTask) {
        Optional<Tasks> taskBd = tasksRepository.getTasks(tokenTask);
        if (taskBd.isEmpty()){
            throw new NotFoundException(Constants.TASK_BY_TOKEN_TASK_NOT_FOUND.getMessage());
        }
        taskBd.get().setTaskTask(tasks.getTaskTask());
        taskBd.get().setInProgressTask(tasks.getInProgressTask());
        taskBd.get().setFinishedTask(tasks.getFinishedTask());
        return tasksRepository.save(taskBd.get());
    }

    public List<TaskJoin> getTasksByTokenTask(String tokenTask) {
        return tasksRepository.getTasksByTokenTask(tokenTask);
    }

    public List<TaskJoin> getTasks() {
        return (List<TaskJoin>) tasksRepository.getTasks();
    }

    public boolean deleteTask(String tokenTask){
        Optional<Tasks> tasks = tasksRepository.getTasks(tokenTask);
        if (tasks.isEmpty()){
            throw new NotFoundException(Constants.TASK_BY_TOKEN_TASK_NOT_FOUND.getMessage());
        }
        tasksRepository.delete(tasks.get());
        return true;
    }
}
