package com.iobuilders.bank.infrastructure.controller;

import com.iobuilders.bank.application.service.UserService;
import com.iobuilders.bank.domain.User;
import com.iobuilders.bank.domain.exception.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Set of endpoints for User Services")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all the users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the user")
    public User getUser(@PathVariable("id") int id) throws EntityNotFoundException {
        return userService.getUserById(id);
    }

    @PostMapping
    @Operation(summary = "Save the User")
    public Long saveUser(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return user.getId();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the User")
    public void deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
    }
}
