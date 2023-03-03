package com.example.airconditionsystem.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.airconditionsystem.bean.Rmonitor;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:12
 */

@Mapper
public interface RmonitorMapper extends BaseMapper<Rmonitor> {
    /**
     * 通过roomId得到Rmonitor
     * @return Rmonitor
     */
    @Select("select * from rmonitor where roomid = #{roomId}")
    Rmonitor getRmonitorByRoomId(String roomId);

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    @Insert("insert into rmonitor (roomid, rmtemperature, rmwet, rmrsi, rmtime) values (#{roomId}, #{rmTemperature}, #{rmWet}, #{rmRsi}, #{rmTime})")
    int createRmonitor(String roomId, double rmTemperature, double rmWet, double rmRsi, Timestamp rmTime);

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    @Delete("delete from rmonitor where roomid = #{roomId}")
    int deleteRmonitor(String roomId);

    /**
     * 修改机房监控信息,roomId为key
     * @return 成功为1失败为0
     */
    @Update("update rmonitor set rmtemperature = #{rmTemperature}, rmwet = #{rmWet}, rmrsi = #{rmRsi}, rmtime = #{rmTime} where roomid = #{roomId}")
    int updateRmonitor(String roomId, double rmTemperature, double rmWet, double rmRsi, Timestamp rmTime);
}
