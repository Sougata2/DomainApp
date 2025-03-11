package com.sougata.domainApp.User.controller;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.User.domain.errors.UserNotFoundException;
import com.sougata.domainApp.User.dto.request.UserRequest;
import com.sougata.domainApp.User.dto.request.UserUpdateRequest;
import com.sougata.domainApp.User.dto.response.UpdateUserResponse;
import com.sougata.domainApp.User.dto.response.UserResponse;
import com.sougata.domainApp.User.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/domain/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponseList = new ArrayList<>();
        userService.findAll().forEach(user -> {
            userResponseList.add(UserResponse.builder()
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .email(user.getEmail())
                    .createdAt(user.getCreatedAt())
                    .id(user.getId())
                    .password(user.getPassword())
                    .build());
        });
        return ResponseEntity.ok(userResponseList);
    }

    @PostMapping
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest) {
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(
                UserResponse.builder()
                        .id(savedUser.getId())
                        .firstName(savedUser.getFirstName())
                        .lastName(savedUser.getLastName())
                        .email(savedUser.getEmail())
                        .createdAt(savedUser.getCreatedAt())
                        .password(savedUser.getPassword())
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<UpdateUserResponse> updateUser(@Valid @RequestBody UserUpdateRequest updateRequest) throws UserNotFoundException {
        User updatedUser = userService.updateUser(User.builder()
                .id(updateRequest.getId())
                .firstName(updateRequest.getFirstName())
                .lastName(updateRequest.getLastName())
                .email(updateRequest.getEmail())
                .password(updateRequest.getPassword())
                .build());

        return ResponseEntity.ok(
                UpdateUserResponse.builder()
                        .id(updatedUser.getId())
                        .firstName(updatedUser.getFirstName())
                        .lastName(updatedUser.getLastName())
                        .email(updatedUser.getEmail())
                        .updatedAt(updatedUser.getUpdatedAt())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUser(@PathVariable UUID id) throws UserNotFoundException {
        Optional<User> user = userService.getById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " is not found!");
        }
        return ResponseEntity.ok(
                UserResponse.builder()
                        .id(user.get().getId())
                        .firstName(user.get().getFirstName())
                        .lastName(user.get().getLastName())
                        .email(user.get().getEmail())
                        .createdAt(user.get().getCreatedAt())
                        .password(user.get().getPassword())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
