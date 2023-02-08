package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:16
 */

@Mapper
public interface UserMapper {
    /**
     * 通过ID得到User
     * @return user
     */
    User getUserByUserId(int userId);

    /**
     * 通过userName得到User
     * @return user
     */
    User getUserByUserName(String userName);

    /**
     * 增加用户
     * @return 成功为1失败为0
     */
    int createUser(String userPassword, String userName, String tel, String desc);

    /**
     * 删除用户
     * @return 成功为0失败为-1
     */
    int deleteUser(String userName);

    /**
     * 修改用户信息,userName为key
     * @return 成功为1失败为0
     */
    int updateUser(String userName, String userPassword, String tel, String desc);
}
