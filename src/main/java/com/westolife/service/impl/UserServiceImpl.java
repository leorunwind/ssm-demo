package com.westolife.service.impl;

import com.westolife.dao.UserDAO;
import com.westolife.model.User;
import com.westolife.service.UserService;
import com.westolife.web.exception.InnerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by saigao on 16/8/6.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User findUserByNameOrMail(String userName, String mail) {
        return userDAO.getUserByNameOrMail(userName, mail);
    }

    @Override
    public User verifyUser(String username, String password) {
        User user = userDAO.verifyUser(username, password);
        return user;
    }

    @Override
    public int insertOrUpdate(String username, String password, String mail, int gender) {

        User user = new User();
        user.setUserName(username);
        user.setMail(mail);
        user.setGender(gender);
        user.setPassword(password);
        int result = userDAO.insertOrUpdate(user);
        return result;
    }
}
