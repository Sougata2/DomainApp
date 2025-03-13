package com.sougata.domainApp.User.service;

import com.sougata.domainApp.User.domain.entities.User;
import com.sougata.domainApp.User.domain.errors.UserNotFoundException;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(User user);

    User updateUser(User user) throws UserNotFoundException;

    Optional<User> getById(UUID id);

    void deleteById(UUID id);

    List<User> findAll();

    void addRole(User user, String role) throws Exception;

}
