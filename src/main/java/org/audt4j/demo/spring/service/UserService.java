package org.audt4j.demo.spring.service;

import org.audt4j.demo.spring.model.User;

public interface UserService {

    
    void saveUser(User user);

    User getUserByuserName(String userName);

    void login(String userName, String password);

    void changePassword(String oldPassword, String newPassword);
}
