package com.sougata.domainApp.User.service.impl;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.User.domain.errors.UserNotFoundException;
import com.sougata.domainApp.User.repository.UserRepository;
import com.sougata.domainApp.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

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
        User updatedUser = mergeUser(foundUser.get(), user);
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
        return userRepository.findAll();
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
}
