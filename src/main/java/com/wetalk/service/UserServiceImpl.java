package com.wetalk.service;

import com.wetalk.dao.UserMapper;
import com.wetalk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUser() {

        return userMapper.getUser();
    }
}
