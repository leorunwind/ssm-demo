package com.westolife.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 赛高(runwen.lrw@alibaba-inc.com)
 * 16/8/20.
 */
@Controller
@RequestMapping("/blog/")
public class BlogController {

    @RequestMapping("archives")
    public String listMyBlog() {
        return "blog/archives";
    }

    @RequestMapping("edit")
    public String editBlog() {
        return "blog/edit-blog";
    }
}
