package com.sougata.domainApp.auth.controller;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.User.domain.errors.UserNotFoundException;
import com.sougata.domainApp.User.dto.response.UserResponse;
import com.sougata.domainApp.User.service.UserService;
import com.sougata.domainApp.auth.dto.request.LoginRequest;
import com.sougata.domainApp.auth.dto.request.SignUpRequest;
import com.sougata.domainApp.auth.dto.request.UserRoleRequest;
import com.sougata.domainApp.auth.dto.response.LoginResponse;
import com.sougata.domainApp.auth.dto.response.UserRoleResponse;
import com.sougata.domainApp.auth.service.AuthService;
import com.sougata.domainApp.auth.service.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/domain/auth")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final RoleService roleService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        User savedUser = userService.createUser(
                User.builder()
                        .email(signUpRequest.getEmail())
                        .firstName(signUpRequest.getFirstName())
                        .lastName(signUpRequest.getLastName())
                        .password(signUpRequest.getPassword())
                        .build()
        );
        return ResponseEntity.ok(
                UserResponse.builder()
                        .id(savedUser.getId())
                        .firstName(savedUser.getFirstName())
                        .lastName(savedUser.getLastName())
                        .email(savedUser.getEmail())
                        .password(savedUser.getPassword())
                        .createdAt(savedUser.getCreatedAt())
                        .build()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String token = authService.generateToken(userDetails);

        return ResponseEntity.ok(
                LoginResponse.builder()
                        .email(userDetails.getUsername())
                        .token(token)
                        .expiresIn(84600000L)
                        .build()
        );
    }

    @PostMapping("/add-role")
    public ResponseEntity<UserRoleResponse> addRole(@RequestBody UserRoleRequest userRoleRequest) throws Exception {
        // get the user by id
        Optional<User> foundUser = userService.getById(userRoleRequest.getId());

        // set the role
        if (foundUser.isPresent()) {
            userService.addRole(foundUser.get(), userRoleRequest.getRole());
        } else {
            throw new UserNotFoundException("User with id " + userRoleRequest.getRole() + " not found!");
        }

        // send response.
        return ResponseEntity.ok(
                UserRoleResponse.builder()
                        .id(foundUser.get().getId())
                        .firstName(foundUser.get().getFirstName())
                        .roleName(userRoleRequest.getRole())
                        .build()
        );
    }

    @PostMapping("/create-role")
    public ResponseEntity<String> createRole(@RequestBody Map<String, String> request) {
        roleService.create(request.get("role"));
        return ResponseEntity.ok(request.get("role") + " Role Created!");
    }

}
