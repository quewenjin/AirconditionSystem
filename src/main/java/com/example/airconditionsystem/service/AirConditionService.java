package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.AirCondition;
import com.example.airconditionsystem.mapper.AirConditionMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:21
 */

@Service
@Transactional
public class AirConditionService {
    @Autowired
    AirConditionMapper airConditionMapper;

    /**
     * 通过airId得到AirCondition
     * @return AirCondition
     */
    public AirCondition getAirConditionByAirId(String airId){
        return airConditionMapper.getAirConditionByAirId(airId);
    }

    /**
     * 增加空调
     * @return 成功为1失败为0
     */
    public int createAirCondition(String airId, String airName, String roomId, String airRemarks){
        return airConditionMapper.createAirCondition(airId, airName, roomId, airRemarks);
    }

    /**
     * 删除空调
     * @return 成功为0失败为-1
     */
    public int deleteAirCondition(String airId){
        return airConditionMapper.deleteAirCondition(airId);
    }

    /**
     * 修改空调信息,airId为key
     * @return 成功为1失败为0
     */
    public int updateAirCondition(String airId, String airName, String roomId, String airRemarks){
        return airConditionMapper.updateAirCondition(airId, airName, roomId, airRemarks);
    }

    /**
     * 通过roomId得到List<AirCondition>
     * @return List<AirCondition>
     */
    public List<AirCondition> getAirConditionsByRoomId(String roomId){
        return airConditionMapper.getAirConditionsByRoomId(roomId);
    }

    /**
     * 得到所有List<AirCondition>
     * @return List<AirCondition>
     */
    public List<AirCondition> getAllAirConditions(){
        return airConditionMapper.getAllAirConditions();
    }
}
