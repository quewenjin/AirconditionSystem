package com.example.airconditionsystem.mapper;

import com.example.airconditionsystem.bean.User;
import org.apache.ibatis.annotations.*;

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
    @Select("select * from user where userid = #{userId}")
    User getUserByUserId(int userId);

    /**
     * 通过userName得到User
     * @return user
     */
    @Select("select * from user where username = #{userName}")
    User getUserByUserName(String userName);

    /**
     * 增加用户
     * @return 成功为1失败为0
     */
    @Insert("insert into user (userpassword, username, tel, \"desc\") values (#{userPassword}, #{userName}, #{tel}, #{desc})")
    int createUser(String userPassword, String userName, String tel, String desc);

    /**
     * 删除用户
     * @return 成功为0失败为-1
     */
    @Delete("delete from user where username = #{userName}")
    int deleteUser(String userName);

    /**
     * 修改用户信息,userName为key
     * @return 成功为1失败为0
     */
    @Update("update user set userpassword = #{userPassword}, tel = #{tel}, \"desc\" = #{desc} where username = #{userName}")
    int updateUser(String userName, String userPassword, String tel, String desc);
}
