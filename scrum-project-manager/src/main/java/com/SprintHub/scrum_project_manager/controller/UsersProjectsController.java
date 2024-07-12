package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.UsersProjects;
import com.SprintHub.scrum_project_manager.service.UsersProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersProjectsController {
    @Autowired
    private UsersProjectsService usersProjectsService;

    /*Crear*/
    @PostMapping("userProject/{emailUser}/{tokenProject}")
    public ResponseEntity<UsersProjects> createUsersProjects(@RequestBody UsersProjects usersProjects, @PathVariable String emailUser, @PathVariable String tokenProject) {
        return new ResponseEntity<>(usersProjectsService.createUsersProjects(usersProjects, emailUser, tokenProject), HttpStatus.CREATED);
    }
}
