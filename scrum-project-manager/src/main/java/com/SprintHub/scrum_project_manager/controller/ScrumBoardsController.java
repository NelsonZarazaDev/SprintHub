package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.ScrumBoards;
import com.SprintHub.scrum_project_manager.repository.ScrumBoardsJoin;
import com.SprintHub.scrum_project_manager.service.ScrumBoardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScrumBoardsController {
    @Autowired
    private ScrumBoardsService scrumBoardsService;

    @PostMapping("scrumBoard/{tokenProject}")
    public ResponseEntity<List<ScrumBoards>> createScrumBoard(@RequestBody ScrumBoards scrumBoards, @PathVariable String tokenProject) {
        return new ResponseEntity<>(scrumBoardsService.createScrumBoard(scrumBoards, tokenProject), HttpStatus.CREATED);
    }

    @GetMapping("scrumboard")
    public List<ScrumBoardsJoin> getAllHuScrumboard() {
        return scrumBoardsService.getAllHuScrumboard();
    }
}
