package br.com.gastrovision.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.gastrovision.api.dtos.LoginRequestDto;
import br.com.gastrovision.api.entity.User;
import br.com.gastrovision.api.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers(
            @RequestParam("page") int page,
            @RequestParam("size") int size
    ) {
        List<User> users = userService.findAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{userid}")
    public ResponseEntity<User> getUserById(@PathVariable("userid") String userId) {
        return userService.findById(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userid") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userid}")
    public ResponseEntity<Void> updateUser(@PathVariable("userid") String userId, @RequestBody User user) {
        userService.updateUser(userId, user);
        return ResponseEntity.noContent().build();
    }

// Endpoint para buscar usuarios pelo seu nome
   @GetMapping("/search")
    public ResponseEntity<List<User>> findByName(@RequestParam("name") String name) {
    List<User> users = userService.findByName(name);
    return ResponseEntity.ok(users);
    }

// Endpoint para validar login do usuario
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequestDto loginRequest) {
        return userService.login(loginRequest.login(), loginRequest.password())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(401).build());
    }
}

