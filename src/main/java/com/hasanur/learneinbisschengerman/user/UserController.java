package com.hasanur.learneinbisschengerman.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // -------------------
    // CREATE (Register)
    // -------------------
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRegisterDto dto
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(dto));
    }

    // -------------------
    // READ ALL USERS
    // -------------------
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // -------------------
    // READ SINGLE USER
    // -------------------
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    // -------------------
    // UPDATE USER
    // -------------------
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @Valid @RequestBody UserRegisterDto dto
    ) {
        return ResponseEntity.ok(userService.updateUser(userId, dto));
    }

    // -------------------
    // DELETE USER
    // -------------------
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build(); // 204
    }
}
