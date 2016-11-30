package com.westolife.dao;

import com.westolife.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by saigao on 16/8/6.
 */
@Repository
public interface UserDAO {

    public User getUserByNameOrMail(@Param("userName") String userName, @Param("mail") String mail);

    public User verifyUser(@Param("userName") String userName, @Param("password") String password);

    public int insertOrUpdate(User user);

}
