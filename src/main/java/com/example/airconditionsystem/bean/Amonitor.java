package com.example.airconditionsystem.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:55
 */

@Data
@TableName("amonitor")
public class Amonitor {
    private int amonitorId; // 空调监测表id
    private String airId;   // 空调id
    private Double amTemperature;   // 温度
    private Double amWet;   // 湿度
    private Double amRsi;   // 输出电平
    private Timestamp amTime;   // 时间
}
