package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.Amonitor;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:08
 */

@Mapper
public interface AmonitorMapper {
    /**
     * 通过airId得到Amonitor
     * @return Amonitor
     */
    Amonitor getAmonitorsByRoomId(String airId);

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    int createAmonitor(String airId, String amTemperature, String amWet, String amRsi, Timestamp amTime);

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    int deleteAmonitor(String airId);

    /**
     * 修改机房监控信息,airId为key
     * @return 成功为1失败为0
     */
    int updateAmonitor(String airId, String amTemperature, String amWet, String amRsi, Timestamp amTime);

    /**
     * 
     * @return List<Amonitor>
     */
    List<Amonitor> getAllAmonitors();
}
