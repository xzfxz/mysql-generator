package com.tqg.zhao.user.dao;

import com.tqg.zhao.user.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "userMapper")
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> queryList4Page();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}