package com.ms.cas.filter;

import static com.ms.cas.constant.AllConstant.AUTHID;
import static com.ms.cas.constant.AllConstant.DESKEY;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ms.cas.constant.AllConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ms.cas.config.LoginUser;
import com.ms.cas.crypt.MyDes;

/**
 * 自定义验证过滤器
 *
 * @author wuketao
 */
@Component
public class AuthenticationFilter implements Filter {

    @Autowired
    private LoginUser loginUser;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    /**
     * 通过cookie检查，判断用户是否登录。
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsRequest = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;

        /*
        如果session检查失败，就重定向到登录页面
         */
        HttpSession session = hsRequest.getSession(true);
        if ((null == session) || (null == session.getAttribute(AllConstant.USERKEY))) {
            //重定向到登录界面
            String uri = hsRequest.getRequestURL().toString();
            hsResponse.sendRedirect(AllConstant.URIROOTPATH + "?target=" + uri);
        }

        //通过过来的url，继续执行。
        chain.doFilter(hsRequest, hsResponse);
    }

    @Override
    public void destroy() {
    }
}
