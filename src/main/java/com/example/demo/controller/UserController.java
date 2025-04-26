package com.example.demo.controller;

import com.example.demo.repository.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.getCreatedUsers();
    }

    @PostMapping("")
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping(path = "{id}")
    public User updateUser(@PathVariable Long id, @RequestParam(required = false) String email, @RequestParam(required = false) String name) {
        return userService.update(id, email, name);
    }

}
