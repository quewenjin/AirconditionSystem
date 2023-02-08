package com.example.airconditionsystem.bean;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:50
 */
public class Room {
    private String roomId;  // 机房id
    private String roomName;    // 机房名
    private String roomAddress; // 机房位置
    private String roomRemarks; // 相关备注

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress;
    }

    public String getRoomRemarks() {
        return roomRemarks;
    }

    public void setRoomRemarks(String roomRemarks) {
        this.roomRemarks = roomRemarks;
    }
}
