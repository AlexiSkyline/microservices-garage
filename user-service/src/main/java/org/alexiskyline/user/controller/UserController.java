package org.alexiskyline.user.controller;

import lombok.RequiredArgsConstructor;
import org.alexiskyline.user.entity.User;
import org.alexiskyline.user.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        User newUser = this.userService.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> allUser = this.userService.findAll();
        if (allUser.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User foundUser = this.userService.findById(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundUser);
    }
}