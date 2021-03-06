package com.wetalk.service;

import com.wetalk.dao.GroupMapper;
import com.wetalk.pojo.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    GroupMapper groupMapper;

    @Autowired
    StringRedisTemplate redisTemplate;

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
    public String getMessage(int groupId) {
        String ms = "";
        if (redisTemplate.opsForValue().get(groupId+"") == null){
            ms = "";
        }else{
            ms = redisTemplate.opsForValue().get(groupId+"");
        }
        return ms;
    }

    @Override
    public int deleteGroup(int groupId) {
        int i = groupMapper.deleteGroup(groupId);
        autoInc();
        return i;
    }

    @Override
    public int getLastId() {
       return groupMapper.getLastId();
    }

    @Override
    public int autoInc() {
        return groupMapper.autoInc();
    }
}
