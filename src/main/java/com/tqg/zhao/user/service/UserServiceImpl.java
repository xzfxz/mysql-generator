package com.tqg.zhao.user.service;

import com.tqg.zhao.user.bean.User;
import com.tqg.zhao.user.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/***
 *  @description
 *  @author zhao
 *  @date: 2018/9/29
 *  @version: v1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    @Override
    public int insert(User record) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return null;
    }

    @Override
    public List<User> queryList4Page() {
        return userMapper.queryList4Page();
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return 0;
    }
}
