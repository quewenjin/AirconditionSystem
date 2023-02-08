package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.Amonitor;
import com.example.airconditionsystem.mapper.AmonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:21
 */

@Service
@Transactional
public class AmonitorService {
    @Autowired
    AmonitorMapper amonitorMapper;

    /**
     * 通过airId得到Amonitor
     * @return Amonitor
     */
    public Amonitor getAmonitorsByRoomId(String airId){
        return amonitorMapper.getAmonitorsByRoomId(airId);
    }

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    public int createAmonitor(String airId, String amTemperature, String amWet, String amRsi, Timestamp amTime){
        return amonitorMapper.createAmonitor(airId, amTemperature, amWet, amRsi, amTime);
    }

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    public int deleteAmonitor(String airId){
        return amonitorMapper.deleteAmonitor(airId);
    }

    /**
     * 修改机房监控信息,airId为key
     * @return 成功为1失败为0
     */
    public int updateAmonitor(String airId, String amTemperature, String amWet, String amRsi, Timestamp amTime){
        return amonitorMapper.updateAmonitor(airId, amTemperature, amWet, amRsi, amTime);
    }
}
