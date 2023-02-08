package com.example.airconditionsystem.bean;

/**
 * @auther quewenjin
 * @time 2023/2/6 9:53
 */
public class User {
    private int userId; // 用户id
    private String userPassword;    // 密码
    private String userName;    // 用户名
    private String tel; // 联系方式
    private String desc;    // 其他备注

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
