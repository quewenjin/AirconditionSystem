package com.example.airconditionsystem.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.airconditionsystem.bean.Amonitor;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:08
 */

@Mapper
public interface AmonitorMapper extends BaseMapper<Amonitor> {
    /**
     * 通过airId得到List<Amonitor>
     * @return List<Amonitor>
     */
    @Select("select * from amonitor where airid = #{airId}")
    List<Amonitor> getAmonitorsByAirId(String airId);

    /**
     * 增加机房监控
     * @return 成功为1失败为0
     */
    @Insert("insert into amonitor (airid, amtemperature, amwet, amrsi, amtime) values (#{airId}, #{amTemperature}, #{amWet}, #{amRsi}, #{amTime})")
    int createAmonitor(String airId, Double amTemperature, Double amWet, Double amRsi, Timestamp amTime);

    /**
     * 删除机房监控
     * @return 成功为0失败为-1
     */
    @Delete("delete from amonitor where airid = #{airId}")
    int deleteAmonitor(String airId);

    /**
     * 修改机房监控信息,airId为key
     * @return 成功为1失败为0
     */
    @Update("update amonitor set amtemperature = #{amTemperature}, amwet = #{amWet}, amrsi = #{amRsi}, amtime = #{amTime} where airid = #{airId}")
    int updateAmonitor(String airId, Double amTemperature, Double amWet, Double amRsi, Timestamp amTime);

    /**
     * 一个时间段内某个airId对应空调所有数据
     * @return List<Amonitor>
     */
    @Select("select * from amonitor where airid = #{airId} and (amtime between #{frontTime} and #{backTime})")
    List<Amonitor> getAmonitorsBetweenTime(String airId, Timestamp frontTime, Timestamp backTime);

    /**
     * 得到所有amtime（去重）
     * @return List<Timestamp>
     */
    @Select("select distinct amtime from amonitor")
    List<Timestamp> getDistinctAmTime();

    /**
     * 通过airId和Timestamp得到Amonitor
     * @return Amonitor
     */
    @Select("select * from amonitor where airid = #{airId} and amtime = #{amTime}")
    Amonitor getAmonitorByAirIdAndAmTime(String airId, Timestamp amTime);
}
