package com.example.airconditionsystem.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:50
 */

@Data
@TableName("room")
public class Room {
    private String roomId;  // 机房id
    private String roomName;    // 机房名
    private String roomAddress; // 机房位置
    private String roomRemarks; // 相关备注
}
