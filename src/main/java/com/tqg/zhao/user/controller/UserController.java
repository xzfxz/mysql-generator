package com.tqg.zhao.user.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.tqg.zhao.user.bean.User;
import com.tqg.zhao.user.dao.UserMapper;
import com.tqg.zhao.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/***
 *  @description
 *  @author zhao
 *  @date: 2018/9/29
 *  @version: v1.0
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "userService")
    private UserService userService;

    @ResponseBody
    @RequestMapping("/mList")
    public String listUser(){
        Map rlt = Maps.newHashMap();
        try {
            List<User> users = userService.queryList4Page();
            rlt.put("users",users);
        }catch (Exception e){
            rlt.put("error",e.getMessage());
        }

        return JSON.toJSONString(rlt);
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "uid",required = false)String uid){
        Map rlt = Maps.newHashMap();
        rlt.put("name","zhao");
        rlt.put("uid",uid);
        return JSON.toJSONString(rlt);
    }



}
