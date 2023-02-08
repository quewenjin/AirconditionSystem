package com.example.airconditionsystem.controller;

import com.alibaba.fastjson.JSONArray;
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

    /**
     * 计算并更新后台数据Rmonitor
     * @param params roomId
     * @return json
     */
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


    /**
     * 获得一段时间内的监控数据
     * @param params airId, frontTime, backTime
     * @return json
     */
    @RequestMapping(value = "/getDataBetweenTime", method = RequestMethod.POST)
    public String getDataBetweenTime(@RequestBody Map<String, Object> params) {
        String theRoomId = params.get("roomId").toString();
        Timestamp frontTime = new Timestamp(Long.parseLong(params.get("frontTime").toString()));
        Timestamp backTime = new Timestamp(Long.parseLong(params.get("backTime").toString()));
        JSONObject json = new JSONObject(); // 最外层
        JSONArray jsonArray = new JSONArray();  // data层5个key：4个object1个array
        List<AirCondition> theAirConditions = airConditionService.getAirConditionsByRoomId(theRoomId);
        for (AirCondition theAirCondition : theAirConditions) {
            String theAirId = theAirCondition.getAirId();
            JSONObject jsonObject = new JSONObject();
            JSONArray theJsonArray = new JSONArray();   // details层每个object：4个object
            List<Amonitor> amonitors = amonitorService.getAmonitorsBetweenTime(theAirId, frontTime, backTime);
            int num = amonitors.size();
            double cntOfTemperatures = 0;
            double cntOfWets = 0;
            double cntOfRsis = 0;
            for (Amonitor amonitor: amonitors) {
                JSONObject theJsonObject = new JSONObject();
                theJsonObject.put("amTemperature", amonitor.getAmTemperature());
                theJsonObject.put("amWet", amonitor.getAmWet());
                theJsonObject.put("amRsi", amonitor.getAmRsi());
                theJsonObject.put("amTime", amonitor.getAmTime());
                cntOfTemperatures = cntOfTemperatures + amonitor.getAmTemperature();
                cntOfWets = cntOfWets + amonitor.getAmWet();
                cntOfRsis = cntOfRsis + amonitor.getAmRsi();
                theJsonArray.add(theJsonObject);
            }
            double theRmTemperature = cntOfTemperatures/num;
            double theRmWet = cntOfWets/num;
            double theRmRsi = cntOfRsis/num;
            jsonObject.put("airId", theAirId);
            jsonObject.put("details", theJsonArray);
            jsonObject.put("averageTemperature", theRmTemperature);
            jsonObject.put("averageWet", theRmWet);
            jsonObject.put("averageRsi", theRmRsi);
            jsonArray.add(jsonObject);
        }
        json.put("data", jsonArray);
        json.put("status", "no");
        return json.toString();
//        返回json样例：
//        {"data":[{"averageTemperature":19.5,"averageWet":22.5,"averageRsi":200.0,"details":[{"amWet":23.0,"amTime":1675818000000,"amRsi":200.0,"amTemperature":20.0},{"amWet":22.0,"amTime":1675821600000,"amRsi":200.0,"amTemperature":19.0}],"airId":"10001"},{"averageTemperature":21.5,"averageWet":31.5,"averageRsi":220.0,"details":[{"amWet":32.0,"amTime":1675818000000,"amRsi":220.0,"amTemperature":22.0},{"amWet":31.0,"amTime":1675821600000,"amRsi":220.0,"amTemperature":21.0}],"airId":"10002"},{"averageTemperature":29.5,"averageWet":19.5,"averageRsi":240.0,"details":[{"amWet":20.0,"amTime":1675818000000,"amRsi":240.0,"amTemperature":30.0},{"amWet":19.0,"amTime":1675821600000,"amRsi":240.0,"amTemperature":29.0}],"airId":"10003"}],"status":"no"}
    }
}
