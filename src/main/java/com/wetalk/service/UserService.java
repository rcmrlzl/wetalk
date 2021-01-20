package com.wetalk.service;

import com.wetalk.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService{
    List<User> getUser();
}