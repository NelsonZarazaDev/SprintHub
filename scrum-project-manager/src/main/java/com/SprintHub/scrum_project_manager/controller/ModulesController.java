package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Modules;
import com.SprintHub.scrum_project_manager.service.ModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModulesController {
    @Autowired
    private ModulesService modulesService;

    @PostMapping("module/{tokenEpic}/{tokenProject}")
    public ResponseEntity<Modules> createModule(@RequestBody Modules modules, @PathVariable String tokenEpic, @PathVariable String tokenProject) {
        return new ResponseEntity<>(modulesService.createModule(modules, tokenEpic, tokenProject), HttpStatus.CREATED);
    }

    @GetMapping("modulesByProject/{tokenProject}")
    public ResponseEntity<List<Modules>> getModulesByPojectIdToken(@PathVariable String tokenProject) {
        return ResponseEntity.ok(modulesService.getModulesByPojectIdToken(tokenProject));
    }

    @GetMapping("module/{tokenModule}")
    public ResponseEntity<Modules> getModulesByTokenModule(@PathVariable String tokenModule) {
        return ResponseEntity.ok(modulesService.getModulesByTokenModule(tokenModule));
    }

    @PutMapping("module/{tokenModule}")
    public ResponseEntity<Modules> updateModule(@RequestBody Modules modules, @PathVariable String tokenModule) {
        return new ResponseEntity<>(modulesService.updateModule(modules, tokenModule), HttpStatus.OK);
    }
}
