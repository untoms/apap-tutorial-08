package com.apap.tu08.service;

import com.apap.tu08.model.UserModel;
import com.apap.tu08.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDb userDb;

    @Autowired
    public UserServiceImpl(UserDb userDb) {
        this.userDb = userDb;
    }

    @Override
    public UserModel addUser(UserModel user) {
        user.setPassword(encrypt(user.getPassword()));
        return userDb.save(user);
    }

    @Override
    public String encrypt(String pasword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode(pasword);
        return hashed;
    }

    @Override
    public UserModel findUserByUsername(String username) {
        return userDb.findByUsername(username);
    }
}
