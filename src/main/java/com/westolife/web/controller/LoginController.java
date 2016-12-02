package com.westolife.web.controller;

import com.westolife.model.User;
import com.westolife.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by saigao on 16/8/6.
 */
@Controller
public class LoginController {

    private final String ADMIN = "w-admin";

    private final String ADMIN_MAIL = "luo@qq.com";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "userinfo")
    @ResponseBody
    // TODO 注意不能出现username和mail分别属于两条记录的情况
    public User userInfo(@RequestParam(value = "name", required = false) String userName,
                         @RequestParam(value = "mail", required = false) String mail,
                         HttpSession session) {
        if (userName == null && mail == null) {
            return null;
        }
        String curUser = session.getAttribute("mail").toString();
        if (!curUser.equals(mail) && !curUser.equals(ADMIN_MAIL)) {
            return null; // 除管理员外不能查询其他用户的信息
        }
        return userService.findUserByNameOrMail(userName, mail);
    }

    @RequestMapping(value = "{username}")
    @ResponseBody
    public User findByUsername(@PathVariable("username") String userName, HttpSession session) {
        String curUser = session.getAttribute("user").toString();
        if (!curUser.equals(userName) && !curUser.equals(ADMIN)) {
            return null; // 除管理员外不能查询其他用户的信息
        }
        return userService.findUserByNameOrMail(userName, "");
    }

    @RequestMapping(value = "myhome")
    public String userCenter(ModelMap modelMap, HttpSession session) {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", session.getAttribute("user"));
        userInfo.put("mail", session.getAttribute("mail"));
//        modelMap.put("name", session.getAttribute("user"));
//        modelMap.put("mail", session.getAttribute("mail"));
        modelMap.put("userInfo", userInfo);
        return "user/myhome";
    }

    @RequestMapping(value = "login")
    public String userLogin() {
        return "user/login";
    }

    @RequestMapping(value = "login", params = "action=submit", method = RequestMethod.POST)
    @ResponseBody
    public Map validate(@RequestParam("usernameOrMail") String usernameOrMail,
                        @RequestParam("password") String password,
                        HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.verifyUser(usernameOrMail, password);
        map.put("result", user);
        if (user != null) {
            session.setAttribute("user", user.getUserName()); // 设置session,保持登录
            session.setAttribute("mail", user.getMail());
        }
        return map;
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        session.setAttribute("mail", null);
        return "user/login";
    }

    @RequestMapping(value = "signup")
    public String signup() {
        return "user/signup";
    }

    @RequestMapping(value = "signup", params = "action=submit", method = RequestMethod.POST)
    @ResponseBody
    public int createUser(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          @RequestParam("mail") String mail,
                          @RequestParam("gender") String gender,
                          HttpSession session) {
        int result = userService.insertOrUpdate(username, password, mail, Integer.parseInt(gender));
        if (result > 0) {
            session.setAttribute("user", username); // 设置session,保持登录
            session.setAttribute("mail", mail);
        }
        return result;
    }

}
