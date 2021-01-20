package com.wetalk.service;

import com.wetalk.dao.GroupMapper;
import com.wetalk.dao.GroupUserMapper;
import com.wetalk.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    GroupMapper groupMapper;

    @Override
    public int createGroup(String groupName, int userId) {
        return groupMapper.createGroup(groupName,userId);
    }

    @Override
    public int updateGroupName(int groupId,String groupName) {
        return groupMapper.updateGroupName(groupId,groupName);
    }

    @Override
    public List<Group> queryGroupByName(String groupName) {
        return groupMapper.queryGroupByName(groupName);
    }

    @Override
    public Group queryGroupById(String groupId) {
        return groupMapper.queryGroupById(groupId);
    }

    @Override
    public int deleteGroup(int groupId) {
        return groupMapper.deleteGroup(groupId);
    }

    @Override
    public int autoInc() {
        return groupMapper.autoInc();
    }
}
