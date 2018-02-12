package com.ms.cas.controller;

import com.ms.cas.constant.AllConstant;
import com.ms.cas.entity.Application;
import com.ms.cas.entity.Organization;
import com.ms.cas.repository.OrganizationRepository;
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
 * 组织机构信息
 * Created by wuketao on 2018/2/12.
 */
@Controller
@RequestMapping("organization")
public class OrganizationController {
    @Autowired
    private OrganizationRepository organizationRepository;

    /**
     * 组织机构列表
     *
     * @return
     */
    @GetMapping("organizationList")
    public String organizationList() {
        return "application/organizationList";
    }

    /**
     * 获取组织机构分页数据
     *
     * @return
     */
    @GetMapping("organizationPage")
    @ResponseBody
    public Page<Organization> organizationPage() {
        Pageable pageable = new PageRequest(0, 10);
        Page<Organization> page = organizationRepository.findAll(pageable);
        return page;
    }

    /**
     * 添加组织机构
     *
     * @return
     */
    @GetMapping("addOrganization")
    public String addOrganization() {
        return "organization/addOrganization";
    }

    /**
     * 保存 组织机构信息
     * @param organization
     * @param session
     * @return
     */
    @PostMapping("saveOrganization")
    @ResponseBody
    public Map<String, Object> saveOrganization(@RequestBody Organization organization, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);

        organizationRepository.save(organization);
        result.put("flag", true);

        return result;
    }
}
