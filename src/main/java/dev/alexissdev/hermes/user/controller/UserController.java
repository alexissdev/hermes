package dev.alexissdev.hermes.user.controller;

import dev.alexissdev.hermes.user.User;
import dev.alexissdev.hermes.user.controller.request.CreateUserRequest;
import dev.alexissdev.hermes.user.controller.request.UpdateUserRequest;
import dev.alexissdev.hermes.user.controller.response.UserResponse;
import dev.alexissdev.hermes.user.factory.UserFactory;
import dev.alexissdev.hermes.user.page.PageResponse;
import dev.alexissdev.hermes.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserFactory userFactory;

    @Autowired
    public UserController(UserService userService, UserFactory userFactory) {
        this.userService = userService;
        this.userFactory = userFactory;
    }

    @GetMapping
    public ResponseEntity<PageResponse> findAll(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable String id) {
        return userService.findById(id)
                .map(UserResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest userRequest) {
        User user = userFactory.from(userRequest);

        return userService.save(user)
                .map(createUser -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(UserResponse.from(user)))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable String id, @Valid @RequestBody UpdateUserRequest request) {
        return userService.update(id, request)
                .map(user -> ResponseEntity.ok(UserResponse.from(user)))
                .orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable String id) {
        return userService.deleteById(id)
                .map(deletedUser -> ResponseEntity.ok().body(deletedUser))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
