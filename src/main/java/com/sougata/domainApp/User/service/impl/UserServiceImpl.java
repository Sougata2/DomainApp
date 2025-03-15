package com.sougata.domainApp.User.service.impl;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.User.domain.errors.UserMergeException;
import com.sougata.domainApp.User.domain.errors.UserNotFoundException;
import com.sougata.domainApp.User.repository.UserRepository;
import com.sougata.domainApp.User.service.UserService;
import com.sougata.domainApp.auth.domain.entity.Role;
import com.sougata.domainApp.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        System.out.println("Saving user : " + savedUser);
        return savedUser;
    }

    @Override
    @Transactional
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findById(user.getId());
        if (foundUser.isEmpty()) {
            throw new UserNotFoundException("failed to UPDATE, user not found!");
        }
        User updatedUser = mergeUser2(foundUser.get(), user);
        System.out.println("UPDATED USER: " + updatedUser);
        return userRepository.save(updatedUser);
    }

    @Override
    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllWithRoles();
    }

    @Transactional
    @Override
    public void addRole(User user, String role) throws Exception {
        Optional<Role> foundRole = roleRepository.findByName(role);
        if (foundRole.isPresent()) {
            user.addRole(foundRole.get());
        } else {
            throw new Exception("role " + role + " not found!");
        }
        userRepository.save(user);
    }

    private User mergeUser(User existingUser, User requestUser) {
        if (requestUser.getFirstName() != null) {
            existingUser.setFirstName(requestUser.getFirstName());
        }
        if (requestUser.getLastName() != null) {
            existingUser.setLastName(requestUser.getLastName());
        }
        if (requestUser.getEmail() != null) {
            existingUser.setEmail(requestUser.getEmail());
        }
        if (requestUser.getPassword() != null) {
            existingUser.setPassword(requestUser.getPassword());
        }

        existingUser.setUpdatedAt(LocalDateTime.now()); // Update timestamp

        return existingUser;
    }

    private User mergeUser2(User existingUser, User requestUser) throws UserMergeException {
        // takes the new values from the requestUser and then paste it in
        // original user. Thus updating existing user
        try {
            for (Field field : existingUser.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object newValue = field.get(requestUser);
                if (newValue != null) {
                    field.set(existingUser, newValue);
                }

            }
        } catch (IllegalAccessException e) {
            throw new UserMergeException();
        }
        return existingUser;
    }
}
