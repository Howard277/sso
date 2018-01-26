package com.ms.web2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * 自定义验证过滤器
 * 
 * @author wuketao
 *
 */
@Component
public class AuthenticationFilter implements Filter {

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    /**
     * 通过session检查，判断用户是否登录。
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsRequest = (HttpServletRequest) request;
        HttpServletResponse hsResponse = (HttpServletResponse) response;
        HttpSession session = hsRequest.getSession(true);
        if (null == session.getAttribute("user")) {
            String ticket = hsRequest.getParameter("ticket");
            if (StringUtils.isEmpty(ticket)) {
                //重定向
                String target = hsRequest.getRequestURL().toString();
                hsResponse.sendRedirect("http://localhost/uam?target=" + target);
                return;
            } else {
                //验证ticket
                // 如果存在ticket，就验证ticket是否有效。
                JSONObject object = restTemplate.getForObject("http://localhost/uam/login/auth?ticket=" + ticket,
                        JSONObject.class);
                if(null!=object){
                    session.setAttribute("user",object);
                }
            }
        }

        chain.doFilter(hsRequest, hsResponse);
    }

    @Override
    public void destroy() {
    }
}
