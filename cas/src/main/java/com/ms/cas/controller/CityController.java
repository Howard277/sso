package com.ms.cas.controller;

import com.ms.cas.constant.AllConstant;
import com.ms.cas.entity.Application;
import com.ms.cas.entity.City;
import com.ms.cas.repository.CityRepository;
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
 * 城市控制器
 * Created by wuketao on 2018/2/12.
 */
@Controller
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    /**
     * 城市列表
     *
     * @return
     */
    @GetMapping("cityList")
    public String cityList() {
        return "city/cityList";
    }

    /**
     * 获取应用系统分页数据
     *
     * @return
     */
    @GetMapping("cityPage")
    @ResponseBody
    public Page<City> cityPage() {
        Pageable pageable = new PageRequest(0, 10);
        Page<City> page = cityRepository.findAll(pageable);
        return page;
    }

    /**
     * 添加城市
     *
     * @return
     */
    @GetMapping("addCity")
    public String addCity() {
        return "city/addCity";
    }

    /**
     * 保存 城市信息
     *
     * @param city    城市信息
     * @return
     */
    @PostMapping("saveCity")
    @ResponseBody
    public Map<String, Object> saveCity(@RequestBody City city) {
        Map<String, Object> result = new HashMap<>();
        result.put("flag", false);

        cityRepository.save(city);
        result.put("flag", true);

        return result;
    }
}
