package com.sougata.domainApp.User.repository;

import com.sougata.domainApp.User.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    // @Modifying
    // @Query("update User u set " +
    // "u.firstName = COALESCE(:firstName, u.firstName), " +
    // "u.lastName = COALESCE(:lastName, u.lastName), " +
    // "u.email = COALESCE(:email, u.email), " +
    // "u.password = COALESCE(:password, u.password), " +
    // "u.createdAt = :updatedAt " +
    // "where u.id = :id")
    // int update(String firstName, String lastName, String email, String password,
    // UUID id, LocalDateTime updatedAt);
    //
    @Query("select u from User u " +
            "left join fetch u.roles")
    List<User> findAllWithRoles();
}
