package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.Rmonitor;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:12
 */

@Mapper
public interface RmonitorMapper {
    /**
     * 通过roomId得到Rmonitor
     * @return Rmonitor
     */
    Rmonitor getRmonitorByRoomId(String roomId);

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    int createRmonitor(String rooomId, double rmTemperature, double rmWet, double rmRsi, Timestamp rmTime);

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    int deleteRmonitor(String rooomId);

    /**
     * 修改机房监控信息,roomId为key
     * @return 成功为1失败为0
     */
    int updateRmonitor(String roomId, double rmTemperature, double rmWet, double rmRsi, Timestamp rmTime);
}
