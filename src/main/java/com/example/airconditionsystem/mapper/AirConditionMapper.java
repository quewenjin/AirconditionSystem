package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.AirCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:03
 */

@Mapper
public interface AirConditionMapper {
    /**
     * 通过airId得到AirCondition
     * @return AirCondition
     */
    AirCondition getAirConditionByAirId(String airId);

    /**
     * 增加空调
     * @return 成功为1失败为0
     */
    int createAirCondition(String airId, String airName, String roomId, String airRemarks);

    /**
     * 删除空调
     * @return 成功为0失败为-1
     */
    int deleteAirCondition(String airId);

    /**
     * 修改空调信息,airId为key
     * @return 成功为1失败为0
     */
    int updateAirCondition(String airId, String airName, String roomId, String airRemarks);

}
