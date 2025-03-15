package com.sougata.domainApp.auth.repository;

import com.sougata.domainApp.User.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :username")
    Optional<User> getByUserNameWithRoles(@Param("username") String username);

}
