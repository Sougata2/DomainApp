package com.sougata.domainApp.auth.repository;

import com.sougata.domainApp.User.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {
    @Query("select u from User u " +
            "where u.email like %:userName%")
    User getByUserName(String userName);
}
