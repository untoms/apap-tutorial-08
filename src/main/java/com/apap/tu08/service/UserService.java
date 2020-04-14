package com.apap.tu08.service;

import com.apap.tu08.model.UserModel;

public interface UserService {

    UserModel addUser(UserModel user);
    String encrypt(String pasword);
    UserModel findUserByUsername(String username);

}
