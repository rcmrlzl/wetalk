package com.wetalk.service;

import com.wetalk.dao.GroupUserMapper;
import com.wetalk.pojo.Group;
import com.wetalk.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupUserServiceImpl implements GroupUserService {

    @Autowired
    GroupUserMapper groupUserMapper;

    @Override
    public int addGroupManager(int userId,int groupId) {
        return groupUserMapper.addGroupManager(userId,groupId);
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
    public String queryGroupUserNameById(int userId) {
        return groupUserMapper.queryGroupUserNameById(userId);
    }

    @Override
    public int deleteGroupUser(int userId,int groupId) {
        int i = groupUserMapper.deleteGroupUser(userId,groupId);
        autoInc();
        return i;
    }

    @Override
    public List<Group> queryProject(int userId) {
        return groupUserMapper.queryProject(userId);
    }

    @Override
    public List<User> queryGroupUser(int groupId) {
        return groupUserMapper.queryGroupUser(groupId);
    }

    @Override
    public int queryPrivilege(int userId, int groupId) {
        return groupUserMapper.queryPrivilege(userId,groupId);
    }

    @Override
    public int getLastId() {
        return groupUserMapper.getLastId();
    }

    @Override
    public int autoInc() {
        return groupUserMapper.autoInc();
    }
}
