package com.westolife.dao;

import com.westolife.BaseTest;
import com.westolife.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by saigao on 16/8/6.
 */
public class UserDAOTest extends BaseTest {

    @Autowired
    private UserDAO userDAO;

    private User mockData(String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword("jiushi");
        user.setGender(1);
        user.setMail("luorunwen@westolife.com");
        return user;
    }

    @Test
    public void getUserByName() {
        User user = userDAO.getUserByNameOrMail("w-admin", "luorunwen@qq.com");
        if (user != null) {
            System.out.println(String.format("id: %d, username: %s", user.getId(), user.getUserName()));
        }
    }

    @Test
    public void verifyUserTest() {
        User user = userDAO.verifyUser("w-admin", "jiushi");
        System.out.println(String.format("id: %d, username: %s", user.getId(), user.getUserName()));
    }

    @Test
    public void insertOrUpdate() {
        User user = mockData("test111");
        int result = userDAO.insertOrUpdate(user);
        System.out.print(result);
    }
}
