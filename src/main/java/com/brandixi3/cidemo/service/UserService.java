package com.brandixi3.cidemo.service;

import com.brandixi3.cidemo.model.User;

public interface UserService {

    
    void saveUser(User user);

    User getUserByuserName(String userName);

    void login(String userName, String password);

    void changePassword(String oldPassword, String newPassword);
}
