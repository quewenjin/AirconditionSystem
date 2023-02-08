package com.example.airconditionsystem.bean;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:55
 */
public class Amonitor {
    private int amonitorId; // 空调监测表id
    private String airId;   // 空调id
    private String amTemperature;   // 温度
    private String amWet;   // 湿度
    private String amRsi;   // 输出电平
    private Timestamp amTime;   // 时间

    public int getAmonitorId() {
        return amonitorId;
    }

    public void setAmonitorId(int amonitorId) {
        this.amonitorId = amonitorId;
    }

    public String getAirId() {
        return airId;
    }

    public void setAirId(String airId) {
        this.airId = airId;
    }

    public String getAmTemperature() {
        return amTemperature;
    }

    public void setAmTemperature(String amTemperature) {
        this.amTemperature = amTemperature;
    }

    public String getAmWet() {
        return amWet;
    }

    public void setAmWet(String amWet) {
        this.amWet = amWet;
    }

    public String getAmRsi() {
        return amRsi;
    }

    public void setAmRsi(String amRsi) {
        this.amRsi = amRsi;
    }

    public Timestamp getAmTime() {
        return amTime;
    }

    public void setAmTime(Timestamp amTime) {
        this.amTime = amTime;
    }
}
