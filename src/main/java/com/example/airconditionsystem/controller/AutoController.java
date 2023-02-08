package com.example.airconditionsystem.controller;

import com.example.airconditionsystem.service.AmonitorService;
import com.example.airconditionsystem.service.RmonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 数据库定时自动更新部分
 */
@Component
public class AutoController {
    @Autowired
    AmonitorService amonitorService;
    @Autowired
    RmonitorService rmonitorService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void updateDataBaseOfMonitor() {



    }
}
