package com.sougata.domainApp.User.repository;

import com.sougata.domainApp.User.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
//    @Modifying
//    @Query("update User u set " +
//            "u.firstName = COALESCE(:firstName, u.firstName), " +
//            "u.lastName = COALESCE(:lastName, u.lastName), " +
//            "u.email = COALESCE(:email, u.email), " +
//            "u.password = COALESCE(:password, u.password), " +
//            "u.createdAt = :updatedAt " +
//            "where u.id = :id")
//    int update(String firstName, String lastName, String email, String password, UUID id, LocalDateTime updatedAt);
//
}
