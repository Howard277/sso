package com.ms.cas.controller;

import com.ms.cas.constant.AllConstant;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 管理控制台controller
 * Created by wuketao on 2018/1/25.
 */
@Controller
@RequestMapping("management")
public class ManagementController {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取redis中存放的tickets集合
     *
     * @return
     */
    @ResponseBody
    @GetMapping("gettickets")
    public Map<Object, Object> getTickets() {
        BoundHashOperations<String, Object, Object> stringObjectObjectBoundHashOperations = redisTemplate.boundHashOps(AllConstant.TICKETS);
        return stringObjectObjectBoundHashOperations.entries();
    }
}
