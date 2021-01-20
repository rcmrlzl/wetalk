package com.wetalk.dao;

import com.wetalk.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Mapper
@Repository
public interface GroupUserMapper {
    //添加群管理
    int addGroupManager(int userId);
    //添加普通群用户
    int addGroupUser(int userId,int groupId);
    //编辑用户权限
    int updateGroupUserPrivilege(int userId,int groupId,int privilege);

    //根据用户名查询群用户
    List<User> queryGroupUserByName(String userName);
    //根据用户id查询群用户
    User queryGroupUserById(int userId,int groupId);
    //删除群用户
    int deleteGroupUser(int userId,int groupId);

    //重置自增长
    int autoInc();


}
