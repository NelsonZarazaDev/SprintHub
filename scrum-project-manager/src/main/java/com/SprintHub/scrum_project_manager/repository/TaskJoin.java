package com.SprintHub.scrum_project_manager.repository;

public interface TaskJoin {
    int getIdTask();
    String getNameTask();
    String getDescriptionTask();
    String getEstimatedTimeTask();
    String getPriorityTask();
    int getResponsibleUserProjectId();
    int getUserStoryId();
    int getModuleId();
    int getEpicId();
    int getProjectId();
    String getTokenTask();
    Boolean getFinishedTask();
    Boolean getInProgressTask();
    Boolean getTaskTask();
    Boolean getStateTask();
    String getTimeTask();
    String getNameHu();
    String getNameProject();
    String getName();
}
