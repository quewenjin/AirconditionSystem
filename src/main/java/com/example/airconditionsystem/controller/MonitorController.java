package com.example.airconditionsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.airconditionsystem.bean.AirCondition;
import com.example.airconditionsystem.bean.Amonitor;
import com.example.airconditionsystem.service.AirConditionService;
import com.example.airconditionsystem.service.AmonitorService;
import com.example.airconditionsystem.service.RmonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @auther quewenjin
 * @time 2023/2/6 17:12
 */

@RestController
@RequestMapping(value = "/monitor")
public class MonitorController {
    @Autowired
    AirConditionService airConditionService;
    @Autowired
    AmonitorService amonitorService;
    @Autowired
    RmonitorService rmonitorService;

    @RequestMapping(value = "/getMonitorData", method = RequestMethod.POST)
    public String test(@RequestBody Map<String, Object> params) {
        String theRoomId = params.get("roomId").toString();
        JSONObject json = new JSONObject();
        List<AirCondition> theAirConditions = airConditionService.getAirConditionsByRoomId(theRoomId);
        int num = theAirConditions.size();
        double cntOfTemperatures = 0;
        double cntOfWets = 0;
        double cntOfRsis = 0;
        for (AirCondition theAirCondition : theAirConditions) {
            Amonitor theAmonitor = amonitorService.getAmonitorByAirId(theAirCondition.getAirId());
            cntOfTemperatures = cntOfTemperatures + theAmonitor.getAmTemperature();
            cntOfWets = cntOfWets + theAmonitor.getAmWet();
            cntOfRsis = cntOfRsis + theAmonitor.getAmRsi();
        }
        double theRmTemperature = cntOfTemperatures/num;
        double theRmWet = cntOfWets/num;
        double theRmRsi = cntOfRsis/num;
        Timestamp updateTime = new Timestamp(System.currentTimeMillis());
        if (rmonitorService.updateRmonitor(theRoomId, theRmTemperature, theRmWet, theRmRsi, updateTime) == 1){
            json.put("status", "yes");
            json.put("rmTemperature", theRmTemperature);
            json.put("rmWet", theRmWet);
            json.put("rmRsi", theRmRsi);
        } else {
            json.put("status", "no");
        }

        return json.toString();
    }
}
