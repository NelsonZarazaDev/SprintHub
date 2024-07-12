package com.SprintHub.scrum_project_manager.controller;

import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/user/{tokenUser}")
    public ResponseEntity<Users> searchUser(@PathVariable String tokenUser) {
        return ResponseEntity.ok(usersService.getUserByEmail(tokenUser));
    }

    @PostMapping("user")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("user")
    public ResponseEntity<Users> updateUser(@RequestBody Users user) {
        return new ResponseEntity<>(usersService.updateUser(user), HttpStatus.OK);
    }

}
