package com.example.airconditionsystem.controller;

import com.example.airconditionsystem.service.AmonitorService;
import com.example.airconditionsystem.service.RmonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther quewenjin
 * @time 2023/2/6 17:12
 */

@RestController
@RequestMapping(value = "/monitor")
public class MonitorController {
    @Autowired
    AmonitorService amonitorService;
    @Autowired
    RmonitorService rmonitorService;
}
