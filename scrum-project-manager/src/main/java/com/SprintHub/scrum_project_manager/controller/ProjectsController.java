package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Projects;
import com.SprintHub.scrum_project_manager.service.ProjectsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectsController {
    @Autowired
    private ProjectsService projectsService;

    /*Create project*/
    @PostMapping("project/{emailUser}")
    public ResponseEntity<Projects> createProject(@Valid @RequestBody Projects projects, @PathVariable String emailUser) {
        return new ResponseEntity<>(projectsService.createProject(projects, emailUser), HttpStatus.CREATED);
    }

    /*Read project*/
    @GetMapping("project/{tokenProject}")
    public ResponseEntity<Projects> getProjectById(@PathVariable String tokenProject) {
        return ResponseEntity.ok(projectsService.getProjectByToken(tokenProject));
    }

    /*Update project*/
    @PutMapping("project")
    public ResponseEntity<Projects> updateProject(@RequestBody Projects project) {
        return new ResponseEntity<>(projectsService.updateProject(project), HttpStatus.OK);
    }

    /*Delete project*/
    @DeleteMapping("project/{tokenProject}")
    public ResponseEntity<String> deleteProjectsByToken(@PathVariable String tokenProject){
        return new ResponseEntity(projectsService.deleteProjectsByToken(tokenProject), HttpStatus.NO_CONTENT);
    }

    /*Read projects by master scrum id*/
    @GetMapping("projectMasterScrum/{masterScrumId}")
    public ResponseEntity<List<Projects>> getProjectMasterScrumById(@PathVariable int masterScrumId) {
        return ResponseEntity.ok(projectsService.getProjectMasterScrumById(masterScrumId));

    }
}
