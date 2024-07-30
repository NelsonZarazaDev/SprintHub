package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Tasks;
import com.SprintHub.scrum_project_manager.repository.TaskJoin;
import com.SprintHub.scrum_project_manager.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TasksController {
    @Autowired
    private TasksService tasksService;

    @PostMapping("task/{tokenHU}/{tokenModule}/{tokenEpic}/{tokenProject}")
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks, @PathVariable String tokenHU, @PathVariable String tokenModule, @PathVariable String tokenEpic, @PathVariable String tokenProject) {
        return new ResponseEntity<>(tasksService.createTask(tasks, tokenHU, tokenModule, tokenEpic, tokenProject), HttpStatus.CREATED);
    }

    @GetMapping("task/{tokenTask}")
    public ResponseEntity<List<TaskJoin>> getTasksByTokenTask(@PathVariable String tokenTask) {
        return ResponseEntity.ok(tasksService.getTasksByTokenTask(tokenTask));
    }

    @GetMapping("task")
    public ResponseEntity<List<TaskJoin>> getTasks() {
        return ResponseEntity.ok(tasksService.getTasks());
    }

    @PutMapping("task/{tokenTask}")
    public ResponseEntity<Tasks> updateTask(@RequestBody Tasks tasks, @PathVariable String tokenTask) {
        return new ResponseEntity<>(tasksService.updateTask(tasks, tokenTask), HttpStatus.OK);
    }

    @PutMapping("timeTask/{tokenTask}")
    public ResponseEntity<Tasks> updateTimeTask(@RequestBody Tasks tasks, @PathVariable String tokenTask) {
        return new ResponseEntity<>(tasksService.updateTimeTask(tasks, tokenTask), HttpStatus.OK);
    }

    @PutMapping("progressTask/{tokenTask}")
    public ResponseEntity<Tasks> updateProgressTask(@RequestBody Tasks tasks, @PathVariable String tokenTask) {
        return new ResponseEntity<>(tasksService.updateProgressTask(tasks, tokenTask), HttpStatus.OK);
    }

    @DeleteMapping("task/{tokenTask}")
    public ResponseEntity<String> deleteTask(@PathVariable String tokenTask) {
        return new ResponseEntity(tasksService.deleteTask(tokenTask), HttpStatus.NO_CONTENT);
    }

}
