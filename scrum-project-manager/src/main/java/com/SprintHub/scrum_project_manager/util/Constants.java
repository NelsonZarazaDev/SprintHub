package com.SprintHub.scrum_project_manager.util;

import lombok.Getter;

import java.util.ResourceBundle;

@Getter
public enum Constants {
    USER_EMAIL_NOT_FOUND("user_const_validUpdate.emailUser"),
    PROJECT_NOT_FOUND("project_const_validateRead.tokenProject");
    private final String message;

    Constants(String s){
        this.message = s;
    }

    public String getMessage() {
        ResourceBundle messages = ResourceBundle.getBundle("messages");
        return messages.getString(message);
    }
}
