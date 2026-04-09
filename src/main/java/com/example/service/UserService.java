package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register — returns null if email already taken, otherwise saves and returns user
    public User register(String name, String email, String password) {
        if (userRepository.findByEmail(email) != null) {
            return null; // email already exists
        }
        User user = new User(name, email, password);
        return userRepository.save(user);
    }

    // Login — returns user if email+password match, otherwise null
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
