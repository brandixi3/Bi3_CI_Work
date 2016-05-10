package com.brandixi3.cidemo.service.impl;

import org.audit4j.core.annotation.Audit;
import org.audit4j.core.annotation.DeIdentify;
import org.audit4j.core.annotation.IgnoreAudit;
import org.springframework.stereotype.Service;

import com.brandixi3.cidemo.model.User;
import com.brandixi3.cidemo.service.UserService;

@Service("userService")
@Audit
public class UserServiceImpl implements UserService {

    @Override
    public void login(String userName, @DeIdentify String password) {
        // Method Body

    }

    @Override
    public User getUserByuserName(String userName) {
        return new User("admin");
    }

    @Override
    public void saveUser(User user) {
        // Method Body

    }

    @Override
    @IgnoreAudit
    public void changePassword(String oldPassword, String newPassword) {
        // Method Body

    }

}
