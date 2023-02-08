package com.example.airconditionsystem.bean;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:00
 */
public class Rmonitor {
    private int rmonitorId; // 机房监测表id
    private String roomId;   // 机房id
    private Double rmTemperature;   // 温度
    private Double rmWet;   // 湿度
    private Double rmRsi;   // 输出电平
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

    public Double getRmTemperature() {
        return rmTemperature;
    }

    public void setRmTemperature(Double rmTemperature) {
        this.rmTemperature = rmTemperature;
    }

    public Double getRmWet() {
        return rmWet;
    }

    public void setRmWet(Double rmWet) {
        this.rmWet = rmWet;
    }

    public Double getRmRsi() {
        return rmRsi;
    }

    public void setRmRsi(Double rmRsi) {
        this.rmRsi = rmRsi;
    }

    public Timestamp getRmTime() {
        return rmTime;
    }

    public void setRmTime(Timestamp rmTime) {
        this.rmTime = rmTime;
    }
}
