package com.westolife.service;

import com.westolife.BaseTest;
import com.westolife.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by saigao on 16/8/6.
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserByNameTest() {
        User user = userService.findUserByNameOrMail("w-admin", "luo@qq.com");
        if (user != null) {
            System.out.println(user.getId());
        }
    }

    @Test
    public void verifyUserTest() {
        User user = userService.verifyUser("wadmin", "jiushi");
        if (user != null) {
            System.out.println(String.format("id: %d, username: %s", user.getId(), user.getUserName()));
        }
        User user1 = userService.verifyUser("luorunwen@qq.com", "jiushi");
        if (user1 != null) {
            System.out.println(String.format("id: %d, username: %s", user1.getId(), user1.getUserName()));
        }
    }
}
