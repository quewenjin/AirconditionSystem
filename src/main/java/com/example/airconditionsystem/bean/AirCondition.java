package com.example.airconditionsystem.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:46
 */

@Data
@TableName("aircondition")
public class AirCondition {
    private String airId;   // 空调id
    private String airName; // 空调名
    private String roomId;  // 所属机房id
    private String airRemarks;  // 相关备注，例如位于机房哪个位置等
}
