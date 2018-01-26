package com.ms.web2.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web2/cookie")
public class CookieController {

    /**
     * 清除所有cookie
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/clear")
    public String clear(HttpServletResponse response, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                // 让所有cookie，直接过期。
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return "ok";
    }
}
