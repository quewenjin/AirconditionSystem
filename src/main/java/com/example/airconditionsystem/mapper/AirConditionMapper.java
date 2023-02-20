package com.example.airconditionsystem.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.airconditionsystem.bean.AirCondition;
import com.example.airconditionsystem.bean.Room;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:03
 */

@Mapper
public interface AirConditionMapper extends BaseMapper<AirCondition> {
    /**
     * 通过airId得到AirCondition
     * @return AirCondition
     */
    @Select("select * from aircondition where airid = #{airId}")
    AirCondition getAirConditionByAirId(String airId);

    /**
     * 增加空调
     * @return 成功为1失败为0
     */
    @Insert("insert into aircondition (airid, airname, roomid, airremarks) values (#{airId}, #{airName}, #{roomId}, #{airRemarks})")
    int createAirCondition(String airId, String airName, String roomId, String airRemarks);

    /**
     * 删除空调
     * @return 成功为0失败为-1
     */
    @Delete("delete from aircondition where airid = #{airId}")
    int deleteAirCondition(String airId);

    /**
     * 修改空调信息,airId为key
     * @return 成功为1失败为0
     */
    @Update("update aircondition set airname = #{airName}, roomid = #{roomId}, airremarks = #{airRemarks} where airid = #{airId}")
    int updateAirCondition(String airId, String airName, String roomId, String airRemarks);

    /**
     * 通过roomId得到List<AirCondition>
     * @return List<AirCondition>
     */
    @Select("select * from aircondition where roomid = #{roomId}")
    List<AirCondition> getAirConditionsByRoomId(String roomId);
}
