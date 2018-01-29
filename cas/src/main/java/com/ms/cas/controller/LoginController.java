package com.ms.cas.controller;

import static com.ms.cas.constant.AllConstant.AUTHID;

import java.io.IOException;
import java.net.URL;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ms.cas.config.LoginUser;
import com.ms.cas.constant.AllConstant;

/**
 * 登录控制器
 *
 * @author wuketao
 */
@Slf4j
@Controller
@RequestMapping(value = {"login", ""})
public class LoginController {

    /**
     * 验证列表
     */
    @Autowired
    private LoginUser loginUser;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Value("${sessiontimeout}")
    private Integer sessiontimeout;

    @Value("${cookietimeout}")
    private Integer cookietimeout;

    /**
     * 进入登录界面
     *
     * @return
     * @throws Exception
     * @throws IOException
     */
    @GetMapping({"login", ""})
    public String login(@RequestParam(value = "target", required = false) String target, HttpServletResponse response, HttpSession session)
            throws Exception {
        /*
         访问登录界面有两种情况，通过是否有target来区分：
         情况一，无target。表示要登录“统一授权系统”
         情况二，有target。表示重定向到“统一授权系统”
          */
        if (!StringUtils.isBlank(target)) {
            // 通过检查session中的user，判断是否登录过
            if (null == session.getAttribute(AllConstant.USERKEY)) {
                //对于未登录的情况，需要正常进入登录界面并将目标路径保存到session中
                session.setAttribute("target", target);
                session.setMaxInactiveInterval(sessiontimeout);
            } else {
                //对于已经登录的情况，直接创建ticket并重定向到目标路径
                String ticket = UUID.randomUUID().toString();
                BoundHashOperations<String, Object, Object> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(AllConstant.TICKETS);
                stringObjectObjectBoundHashOperations.put(ticket, session.getAttribute(AllConstant.USERKEY));
                //重定向到目标路径并带有ticket
                return "redirect:" + target + "?ticket=" + ticket;
            }
        }

        // 设置cookie为持久化cookie
        Cookie cookie = new Cookie("SESSION",session.getId());
        cookie.setMaxAge(cookietimeout);
        response.addCookie(cookie);
        log.info(session.getId());
        return "/login/login";
    }

    /**
     * 登录检查
     *
     * @return
     */
    @RequestMapping("/check")
    public String check(@RequestParam("username") String username, @RequestParam("password") String password,
                        HttpServletResponse response, Model model, HttpSession session, HttpServletRequest request) {
        if ((null != username) && (null != password) && (username.equals(password))) {
            // session设置
            session.setAttribute(AllConstant.USERKEY, username);
            log.info(session.getId());

            String target = session.getAttribute("target") != null ? session.getAttribute("target").toString() : "";
            if (!StringUtils.isBlank(target)) {
                //清除旧target
                session.setAttribute("target", "");
                //返回到重定向目录
                //判断重定向地址是当前系统还是其他业务系统
                String newurl = "";
                try {
                    if (new URL(target).getPath().indexOf(AllConstant.URIROOTPATH) == 0) {
                        newurl = "redirect:" + target;
                    } else {
                        //其他系统重定向，需要创建一个临时ticket。
                        // 设置跳转位置。生产一个随机ID，作为ticket。
                        String ticket = UUID.randomUUID().toString();
                        BoundHashOperations<String, Object, Object> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(AllConstant.TICKETS);
                        stringObjectObjectBoundHashOperations.put(ticket, username);
                        //组装新url
                        newurl = "redirect:" + target + "?ticket=" + ticket;
                    }
                } catch (Exception ex) {
                    log.error("{}", ex);
                }
                return newurl;
            }
            //返回默认目录
            return "redirect:/user/list";
        } else {
            return "/login/login";
        }
    }

    /**
     * 提出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.setMaxInactiveInterval(0);

        // 退出后，进入到登录界面
        return "/login/login";
    }

    /**
     * 验证ticket是否有效
     *
     * @param ticket
     * @return
     */
    @RequestMapping("auth")
    @ResponseBody
    public JSONObject auth(@RequestParam("ticket") String ticket) {
        JSONObject object = new JSONObject();
        // 验证ticket是否存在，如果存在就返回相应内容，同时删除这个ticket，防止别人盗用。
        BoundHashOperations<String, Object, Object> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(AllConstant.TICKETS);
        Object user = stringObjectObjectBoundHashOperations.get(ticket);
        if (null != user) {
            object.put("user", user.toString());
            stringObjectObjectBoundHashOperations.delete(ticket);
        }
        // 验证ticket是否正确
        return object;
    }

    @RequestMapping("clear")
    @ResponseBody
    public String clear(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(1);
                response.addCookie(cookie);
            }
        }
        if (loginUser.getTicketList() != null) {
            loginUser.getTicketList().clear();
        }
        return "ok";
    }
}
