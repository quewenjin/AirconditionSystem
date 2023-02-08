package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.Room;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:14
 */

@Mapper
public interface RoomMapper {
    /**
     * 通过roomId得到Room
     * @return room
     */
    Room getRoomByRoomId(String roomId);

    /**
     * 增加机房
     * @return 成功为1失败为0
     */
    int createRoom(String roomId, String roomName, String roomAddress, String roomRemarks);

    /**
     * 删除机房
     * @return 成功为0失败为-1
     */
    int deleteRoom(String roomId);

    /**
     * 修改机房信息,roomId为key
     * @return 成功为1失败为0
     */
    int updateRoom(String roomId, String roomName, String roomAddress, String roomRemarks);
}
