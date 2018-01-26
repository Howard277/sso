package com.ms.cas.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 登录用户类
 * 
 * @author wuketao
 *
 */
@Data
@Component
public class LoginUser {

    /**
     * ticket列表
     */
    private List<String> ticketList = new ArrayList<>();

    private List<String> userList = new ArrayList<>();
}
