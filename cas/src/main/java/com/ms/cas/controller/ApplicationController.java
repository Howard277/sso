package com.ms.cas.controller;

import com.ms.cas.constant.AllConstant;
import com.ms.cas.entity.Application;
import com.ms.cas.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 应用系统 控制器
 * Created by wuketao on 2018/2/11.
 */
@Controller
@RequestMapping("application")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    /**
     * 应用系统列表
     *
     * @return
     */
    @GetMapping("applicationList")
    public String applicationList() {
        return "application/applicationList";
    }

    /**
     * 获取应用系统分页数据
     *
     * @return
     */
    @GetMapping("applicationPage")
    @ResponseBody
    public Page<Application> applicationPage() {
        Pageable pageable = new PageRequest(0, 10);
        Page<Application> page = applicationRepository.findAll(pageable);
        return page;
    }

    /**
     * 添加系统
     *
     * @return
     */
    @GetMapping("addApplication")
    public String addApplication() {
        return "application/addApplication";
    }

    /**
     * 保存 应用系统信息
     *
     * @param appCode
     * @param appName
     * @param description
     * @return
     */
    @PostMapping("saveApplication")
    @ResponseBody
    public Map<String, Object> saveApplication(@RequestParam String appCode, @RequestParam String appName, @RequestParam String description, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);

        Application application = new Application();
        application.setAppCode(appCode);
        application.setAppName(appName);
        application.setCreateTime(new Date());
        application.setCreator(session.getAttribute(AllConstant.USERKEY).toString());
        application.setDescription(description);
        applicationRepository.save(application);
        result.put("flag", true);

        return result;
    }
}
