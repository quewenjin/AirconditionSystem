package com.example.airconditionsystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.airconditionsystem.bean.User;
import com.example.airconditionsystem.service.UserService;
import com.example.airconditionsystem.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @auther quewenjin
 * @time 2023/2/6 10:24
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@RequestBody Map<String, Object> params) {
        String theName = params.get("userName").toString();
        String thePassword = params.get("userPassword").toString();
        JSONObject json = new JSONObject();
        User theUser = userService.getUserByUserName(theName);
        if (theUser == null || !thePassword.equals(theUser.getUserPassword())){
            json.put("code", "403");
        } else if (thePassword.equals(theUser.getUserPassword())){
            String token = TokenUtil.generateToken(theUser);
            json.put("token", token);
            json.put("code", "200");
        } else {
            json.put("code", "500");
        }
        return json.toString();
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test(@RequestBody Map<String, Object> params) {
        JSONObject json = new JSONObject();
        json.put("status", "no");
        return json.toString();
    }
}
