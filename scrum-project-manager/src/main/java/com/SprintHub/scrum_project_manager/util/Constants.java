package com.SprintHub.scrum_project_manager.util;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public enum Constants {
    USER_EMAIL_NOT_FOUND("user_const_validUpdate.emailUser"),
    PROJECT_NOT_FOUND("project_const_validateRead.tokenProject"),
    USERS_PROJECT_NOT_FOUNT("users_project_const_project_const_validateRead"),
    USER_EMAIL_EXISTS("user_email_exist"),
    CREDENTIAL_INVALID("credential_invalid"),
    EPIC_BY_TOKEN_EPIC_NOT_FOUND("epic_by_token_epic"),
    MODULE_BY_TOKEN_MODULE_NOT_FOUND("module_by_token_module"),
    TASK_BY_TOKEN_TASK_NOT_FOUND("task_by_token_tasK"),
    USER_STORY_TOKEN_HU_BY_TOKEN_NOT_FOUND("user_story_token_hu");

    private final String message;

    Constants(String s){
        this.message = s;
    }

    public String getMessage() {
        ResourceBundle messages = ResourceBundle.getBundle("messages");
        return messages.getString(message);
    }
}
