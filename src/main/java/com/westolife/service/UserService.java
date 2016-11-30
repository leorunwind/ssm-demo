package com.westolife.service;

import com.westolife.model.User;

/**
 * Created by saigao on 16/8/6.
 */
public interface UserService {

    public User findUserByNameOrMail(String userName, String mail);

    public User verifyUser(String username, String password);

    public int insertOrUpdate(String username, String password, String mail, int gender);
}
