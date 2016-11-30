package com.westolife.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by saigao on 16/8/6.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String index(ModelMap modelMap, HttpSession session) {
        modelMap.put("name", session.getAttribute("user"));
        return "index";
    }

    @RequestMapping(value = "/error")
    public String error(ModelMap modelMap) {
        return "error";
    }

}