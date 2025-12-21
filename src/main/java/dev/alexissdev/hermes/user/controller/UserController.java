package dev.alexissdev.hermes.user.controller;

import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.page.PageResponse;
import dev.alexissdev.hermes.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<PageResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return userService.save(user)
                .map(createUser -> ResponseEntity.status(HttpStatus.CREATED).body(createUser))
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        return userService.deleteById(id)
                .map(deletedUser -> ResponseEntity.ok().body(deletedUser))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
