package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.Amonitor;
import com.example.airconditionsystem.mapper.AmonitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

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
     * 通过airId得到List<Amonitor>
     * @return List<Amonitor>
     */
    public List<Amonitor> getAmonitorsByAirId(String airId){
        return amonitorMapper.getAmonitorsByAirId(airId);
    }

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    public int createAmonitor(String airId, Double amTemperature, Double amWet, Double amRsi, Timestamp amTime){
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
    public int updateAmonitor(String airId, Double amTemperature, Double amWet, Double amRsi, Timestamp amTime){
        return amonitorMapper.updateAmonitor(airId, amTemperature, amWet, amRsi, amTime);
    }

    /**
     * 未测试
     * 一个时间段内某个airId对应空调所有数据
     * @return List<Amonitor>
     */
    public List<Amonitor> getAmonitorsBetweenTime(String airId, Timestamp frontTime, Timestamp backTime){
        return amonitorMapper.getAmonitorsBetweenTime(airId, frontTime, backTime);
    }

    /**
     * 得到所有amtime（去重）
     * @return List<Timestamp>
     */
    public List<Timestamp> getDistinctAmTime(){
        return amonitorMapper.getDistinctAmTime();
    }

    /**
     * 通过airId和Timestamp得到Amonitor
     * @return Amonitor
     */
    public Amonitor getAmonitorByAirIdAndAmTime(String airId, Timestamp amTime){
        return amonitorMapper.getAmonitorByAirIdAndAmTime(airId, amTime);
    }
}
