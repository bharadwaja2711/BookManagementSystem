package com.manoj.bookmanagement.service;

import com.manoj.bookmanagement.model.User;
import com.manoj.bookmanagement.repository.UserRepository;
import com.manoj.bookmanagement.utils.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(String username, String password) {
        String encryptedPassword = encryptPassword(password);
        User user = new User(username, encryptedPassword);
        return userRepository.save(user);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepository.findByUsername(username) != null;
    }

    private String encryptPassword(String password) {
        return AESUtil.encrypt(password);
    }
}
