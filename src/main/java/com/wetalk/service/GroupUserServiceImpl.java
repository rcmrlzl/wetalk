package com.wetalk.service;

import com.wetalk.dao.GroupUserMapper;
import com.wetalk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    GroupUserMapper groupUserMapper;

    @Override
    public int addGroupManager(int userId) {
        return groupUserMapper.addGroupManager(userId);
    }

    @Override
    public int addGroupUser(int userId, int groupId) {
        return groupUserMapper.addGroupUser(userId,groupId);
    }


    @Override
    public int updateGroupUserPrivilege(int userId,int groupId,int privilege) {
        return groupUserMapper.updateGroupUserPrivilege(userId,groupId,privilege);
    }

    @Override
    public List<User> queryGroupUserByName(String name) {
        return groupUserMapper.queryGroupUserByName(name);
    }

    @Override
    public User queryGroupUserById(int userId,int groupId) {
        return groupUserMapper.queryGroupUserById(userId,groupId);
    }

    @Override
    public int deleteGroupUser(int userId,int groupId) {
        return groupUserMapper.deleteGroupUser(userId,groupId);
    }

    @Override
    public int autoInc() {
        return groupUserMapper.autoInc();
    }
}
