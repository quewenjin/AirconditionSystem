package com.example.airconditionsystem.bean;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:53
 */

@Data
@TableName("user")
public class User {
    private int userId; // 用户id
    private String userPassword;    // 密码
    private String userName;    // 用户名
    private String tel; // 联系方式
    private String desc;    // 其他备注
}
