package com.wetalk.dao;

import com.wetalk.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Mapper
@Repository
public interface UserMapper {
    List<User> getUser();
}
