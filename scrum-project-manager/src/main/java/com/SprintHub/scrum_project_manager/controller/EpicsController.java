package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Epics;
import com.SprintHub.scrum_project_manager.service.EpicsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EpicsController {
    @Autowired
    private EpicsService epicsService;

    @PostMapping("epic/{tokenProject}")
    public ResponseEntity<Epics> createEpic(@Valid @RequestBody Epics epics, @PathVariable String tokenProject) {
        return new ResponseEntity<>(epicsService.createEpic(epics, tokenProject), HttpStatus.CREATED);
    }

    @GetMapping("epicByProject/{tokenProject}")
    public ResponseEntity<List<Epics>> getEpicdByProjectIdByToken(@PathVariable String tokenProject){
        return ResponseEntity.ok(epicsService.getEpicsByProjectIdByToken(tokenProject));
    }

    @GetMapping("epic/{tokenEpic}")
    public ResponseEntity<Epics> getEpicByTokenEpic(@PathVariable String tokenEpic){
        return ResponseEntity.ok(epicsService.getEpicByTokenEpic(tokenEpic));
    }

    @PutMapping("epic/{tokenEpic}")
    public ResponseEntity<Epics> updateEpicByTokenEpic(@RequestBody Epics epics, @PathVariable String tokenEpic){
        return new ResponseEntity<>(epicsService.updateEpicByTokenEpic(epics, tokenEpic), HttpStatus.OK);
    }

    @DeleteMapping("epic/{tokenEpic}")
    public ResponseEntity<String> deleteEpic(@PathVariable String tokenEpic) {
        return new ResponseEntity(epicsService.deleteEpic(tokenEpic), HttpStatus.NO_CONTENT);
    }
}
