package com.wetalk.dao;

import com.wetalk.pojo.Group;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Mapper
@Repository
public interface GroupMapper {
    //创建群组
    int createGroup(String groupName,int userId);

    //编辑群组信息
    //修改群组名
    int updateGroupName(int groupId,String groupName);

    //根据名称查询群组
    List<Group> queryGroupByName(String groupName);
    //根据名ID查询群组
    Group queryGroupById(String groupId);

    //删除群组
    int deleteGroup(int groupId);

    //重置自增长
    int autoInc();
}
