package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.UserStories;
import com.SprintHub.scrum_project_manager.service.UserStoriesService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserStoriesController {
    @Autowired
    private UserStoriesService userStoriesService;

    @PostMapping("userStorie/{tokenEpic}/{tokenModule}/{tokenProject}")
    public ResponseEntity<UserStories> createUserStories(@RequestBody UserStories userStories, @PathVariable String tokenEpic, @PathVariable String tokenModule, @PathVariable String tokenProject) {
        return new ResponseEntity<>(userStoriesService.createUserStories(userStories, tokenEpic, tokenModule, tokenProject), HttpStatus.CREATED);
    }

    @GetMapping("userStories/{tokenProject}")
    public ResponseEntity<List<UserStories>> getUserStoriesByProjectId(@PathVariable String tokenProject) {
        return ResponseEntity.ok(userStoriesService.getUserStoriesByProjectId(tokenProject));
    }

    @GetMapping("userStorie/{tokenHu}")
    public ResponseEntity<UserStories> getUserStoriesByTokenHu(@PathVariable String tokenHu) {
        return ResponseEntity.ok(userStoriesService.getUserStoriesByTokenHu(tokenHu));
    }

    @PutMapping("userStorie/{tokenHu}")
    public ResponseEntity<UserStories> updateUserStories(@RequestBody UserStories userStories, @PathVariable String tokenHu) {
        return new ResponseEntity<>(userStoriesService.updateUserStories(userStories, tokenHu), HttpStatus.OK);
    }

    @DeleteMapping("userStorie/{tokenHu}")
    public ResponseEntity<String> deleteUserStories(@PathVariable String tokenHu) {
        return new ResponseEntity(userStoriesService.deleteUserStories(tokenHu), HttpStatus.NO_CONTENT);
    }
}
