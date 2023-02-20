package com.example.airconditionsystem.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.airconditionsystem.bean.Room;
import org.apache.ibatis.annotations.*;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:14
 */

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
    /**
     * 通过roomId得到Room
     * @return room
     */
    @Select("select * from room where roomid = #{roomId}")
    Room getRoomByRoomId(String roomId);

    /**
     * 增加机房
     * @return 成功为1失败为0
     */
    @Insert("insert into room (roomid, roomname, roomaddress, roomremarks) values (#{roomId}, #{roomName}, #{roomAddress}, #{roomremarks})")
    int createRoom(String roomId, String roomName, String roomAddress, String roomRemarks);

    /**
     * 删除机房
     * @return 成功为0失败为-1
     */
    @Delete("delete from room where roomid = #{roomId}")
    int deleteRoom(String roomId);

    /**
     * 修改机房信息,roomId为key
     * @return 成功为1失败为0
     */
    @Update("update room set roomname = #{roomName}, roomaddress = #{roomAddress}, roomremarks = {roomRemarks} where roomid = #{roomId}")
    int updateRoom(String roomId, String roomName, String roomAddress, String roomRemarks);
}
