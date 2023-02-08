package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.Room;
import com.example.airconditionsystem.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:20
 */

@Service
@Transactional
public class RoomService {
    @Autowired
    RoomMapper roomMapper;

    /**
     * 通过roomId得到Room
     * @return room
     */
    public Room getRoomByRoomId(String roomId){
       return roomMapper.getRoomByRoomId(roomId);
    }

    /**
     * 增加机房
     * @return 成功为1失败为0
     */
    public int createRoom(String roomId, String roomName, String roomAddress, String roomRemarks){
        return roomMapper.createRoom(roomId, roomName, roomAddress, roomRemarks);
    }

    /**
     * 删除机房
     * @return  成功为0失败为-1
     */
    public int deleteRoom(String roomId){
        return roomMapper.deleteRoom(roomId);
    }

    /**
     * 修改机房信息,roomId为key
     * @return 成功为1失败为0
     */
    public int updateRoom(String roomId, String roomName, String roomAddress, String roomRemarks){
        return roomMapper.updateRoom(roomId, roomName, roomAddress, roomRemarks);
    }
}
