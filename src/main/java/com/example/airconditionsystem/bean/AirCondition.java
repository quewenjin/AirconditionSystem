package com.example.airconditionsystem.bean;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:46
 */
public class AirCondition {
    private String airId;   // 空调id
    private String airName; // 空调名
    private String roomId;  // 所属机房id
    private String airRemarks;  // 相关备注，例如位于机房哪个位置等

    public String getAirId() {
        return airId;
    }

    public void setAirId(String airId) {
        this.airId = airId;
    }

    public String getAirName() {
        return airName;
    }

    public void setAirName(String airName) {
        this.airName = airName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAirRemarks() {
        return airRemarks;
    }

    public void setAirRemarks(String airRemarks) {
        this.airRemarks = airRemarks;
    }
}
