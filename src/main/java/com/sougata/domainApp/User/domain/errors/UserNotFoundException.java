package com.sougata.domainApp.User.domain.errors;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
