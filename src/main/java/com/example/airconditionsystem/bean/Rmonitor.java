package com.example.airconditionsystem.bean;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:00
 */
public class Rmonitor {
    private int rmonitorId; // 机房监测表id
    private String roomId;   // 机房id
    private String rmTemperature;   // 温度
    private String rmWet;   // 湿度
    private String rmRsi;   // 输出电平
    private Timestamp rmTime;   // 时间

    public int getRmonitorId() {
        return rmonitorId;
    }

    public void setRmonitorId(int rmonitorId) {
        this.rmonitorId = rmonitorId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRmTemperature() {
        return rmTemperature;
    }

    public void setRmTemperature(String rmTemperature) {
        this.rmTemperature = rmTemperature;
    }

    public String getRmWet() {
        return rmWet;
    }

    public void setRmWet(String rmWet) {
        this.rmWet = rmWet;
    }

    public String getRmRsi() {
        return rmRsi;
    }

    public void setRmRsi(String rmRsi) {
        this.rmRsi = rmRsi;
    }

    public Timestamp getRmTime() {
        return rmTime;
    }

    public void setRmTime(Timestamp rmTime) {
        this.rmTime = rmTime;
    }
}
