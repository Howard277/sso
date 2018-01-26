package com.ms.cas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by wuketao on 2018/1/25.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/list")
    public String list() {
        return "/view/user/userlist";
    }

}
