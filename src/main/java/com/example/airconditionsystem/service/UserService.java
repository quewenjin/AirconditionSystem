package com.example.airconditionsystem.service;

import com.example.airconditionsystem.bean.User;
import com.example.airconditionsystem.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:19
 */

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 通过ID得到User
     * @return user
     */
    public User getUserByUserId(int userId){
        return userMapper.getUserByUserId(userId);
    }

    /**
     * 通过userName得到User
     * @return user
     */
    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }

    /**
     * 增加用户
     * @return 成功为1失败为0
     */
    public int createUser(String userPassword, String userName, String tel, String desc){
        return userMapper.createUser(userPassword, userName, tel, desc);
    }

    /**
     * 删除用户
     * @return 成功为0失败为-1
     */
    public int deleteUser(String userName){
        return userMapper.deleteUser(userName);
    }

    /**
     * 修改用户信息,userName为key
     * @return 成功为1失败为0
     */
    public int updateUser(String userName, String userPassword, String tel, String desc){
        return userMapper.updateUser(userName, userPassword, tel, desc);
    }

}
