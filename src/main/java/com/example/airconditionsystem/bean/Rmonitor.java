package com.example.airconditionsystem.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:00
 */

@Data
@TableName("rmonitor")
public class Rmonitor {
    private int rmonitorId; // 机房监测表id
    private String roomId;   // 机房id
    private Double rmTemperature;   // 温度
    private Double rmWet;   // 湿度
    private Double rmRsi;   // 输出电平
    private Timestamp rmTime;   // 时间
}
