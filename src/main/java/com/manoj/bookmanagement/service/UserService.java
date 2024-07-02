package com.manoj.bookmanagement.service;

import com.manoj.bookmanagement.model.User;

public interface UserService {

    User registerUser(String username, String password);

    boolean isUsernameTaken(String username);
}
