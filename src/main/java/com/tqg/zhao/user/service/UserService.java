package com.tqg.zhao.user.service;

import com.tqg.zhao.user.bean.User;

import java.util.List;

public interface UserService {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> queryList4Page();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
