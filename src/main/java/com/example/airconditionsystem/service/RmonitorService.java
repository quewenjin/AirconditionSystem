package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.Rmonitor;
import com.example.airconditionsystem.mapper.RmonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:22
 */

@Service
@Transactional
public class RmonitorService {
    @Autowired
    RmonitorMapper rmonitorMapper;

    /**
     * 通过roomId得到Rmonitor
     * @return Rmonitor
     */
    public Rmonitor getRmonitorsByRoomId(String RoomId){
        return rmonitorMapper.getRmonitorsByRoomId(RoomId);
    }

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    public int createRmonitor(String rooomId, String rmTemperature, String rmWet, String rmRsi, Timestamp rmTime){
        return rmonitorMapper.createRmonitor(rooomId, rmTemperature, rmWet, rmRsi, rmTime);
    }

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    public int deleteRmonitor(String rooomId){
        return rmonitorMapper.deleteRmonitor(rooomId);
    }

    /**
     * 修改机房监控信息,roomId为key
     * @return 成功为1失败为0
     */
    public int updateRmonitor(String rooomId, String rmTemperature, String rmWet, String rmRsi, Timestamp rmTime){
        return rmonitorMapper.updateRmonitor(rooomId, rmTemperature, rmWet, rmRsi, rmTime);
    }
}