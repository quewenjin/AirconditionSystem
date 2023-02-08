package com.example.airconditionsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.airconditionsystem.service.AmonitorService;
import com.example.airconditionsystem.service.RmonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 数据库定时自动更新部分
 */
@Component
public class AutoController {
    @Autowired
    AmonitorService amonitorService;
    @Autowired
    RmonitorService rmonitorService;

    /**
     * 计算并更新后台数据Rmonitor
     */
    @Scheduled(cron = "0 0 0 * * ?")    // 执行间隔后期按需修改
    public void updateDataBaseOfMonitor() {

//        请求，解析json，加入am，计算并加入rm

    }
}
