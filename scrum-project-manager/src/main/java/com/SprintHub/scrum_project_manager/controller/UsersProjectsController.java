package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.model.UsersProjects;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsByTokenUsersJoin;
import com.SprintHub.scrum_project_manager.repository.UsersProjectsJoin;
import com.SprintHub.scrum_project_manager.service.UsersProjectsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersProjectsController {
    @Autowired
    private UsersProjectsService usersProjectsService;

    /*Crear*/
    @PostMapping("userProject/{emailUser}/{tokenProject}")
    public ResponseEntity<UsersProjects> createUsersProjects(@Valid @RequestBody UsersProjects usersProjects, @PathVariable String emailUser, @PathVariable String tokenProject) {
        return new ResponseEntity<>(usersProjectsService.createUsersProjects(usersProjects, emailUser, tokenProject), HttpStatus.CREATED);
    }

    /*Read project by token*/
    @GetMapping("usersProject/{tokenProject}")
    public List<UsersProjectsJoin> getUsersProjects(@PathVariable String tokenProject) {
        return usersProjectsService.getUsersProjects(tokenProject);
    }

    /*READ projects*/
    @GetMapping("userProject/{tokenUser}")
    public List<UsersProjectsByTokenUsersJoin> getProjectsUsersProjects(@PathVariable String tokenUser) {
        return usersProjectsService.getProjectsUsersProjects(tokenUser);
    }

    /*UPDATE PROJECT USERS*/
    @PutMapping("userProject/{tokenUserProject}")
    public ResponseEntity<UsersProjects> updateProjectUser(@Valid @RequestBody UsersProjects usersProjects, @PathVariable String tokenUserProject) {
        return new ResponseEntity<>(usersProjectsService.updateProjectUser(usersProjects, tokenUserProject), HttpStatus.OK);
    }

    /*DELETE PROJECT USER*/
    @DeleteMapping("userProject/{tokenUsersProjects}")
    public ResponseEntity<String> deleteUsersProjects(@PathVariable String tokenUsersProjects) {
        return new ResponseEntity(usersProjectsService.deleteUsersProjects(tokenUsersProjects), HttpStatus.NOT_FOUND);
    }
}
