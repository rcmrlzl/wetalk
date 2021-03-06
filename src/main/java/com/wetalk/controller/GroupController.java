package com.wetalk.controller;

import com.wetalk.pojo.Group;
import com.wetalk.pojo.User;
import com.wetalk.service.GroupService;
import com.wetalk.service.GroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GroupController {

        @Autowired
        GroupService groupService;

        @Autowired
        GroupUserService groupUserService;
        int i;
        public String createGroupPage(){
            return "";
        }

        //新建群组
        @RequestMapping("/createGroup/{groupName}/{userId}")
        @ResponseBody
        public int createGroup(@PathVariable("groupName") String name ,@PathVariable("userId")int uid){
            if (groupService.createGroup(name,uid)==1){
                int groupId = groupService.getLastId();
                if (groupUserService.addGroupManager(uid,groupId)==1){
                    return groupId;
                }
            }
            return 0;
        }

        //邀请用户
        @RequestMapping("/inviteUser/{userId}/{groupId}")
        @ResponseBody
        public int inviteUser(@PathVariable("userId")int uid, @PathVariable("groupId") int gid){
            if(groupUserService.queryGroupUserById(uid,gid) != null){
                return 0;
            }else{
                groupUserService.addGroupUser(uid,gid);
                return 1;
            }
        }

        //编辑群组信息
        //修改群名
        @RequestMapping("/updateGroupName/{groupId}/{groupName}")
        @ResponseBody
        public int updateGroupName(@PathVariable("groupId") int gid,@PathVariable("groupName") String name){
            return groupService.updateGroupName(gid,name);
        }
        //修改用户权限
        @RequestMapping("/updateUserPrivilege/{userId}/{groupId}/{privilege}")
        @ResponseBody
        public int updateUserPrivilege(@PathVariable("userId") int uid,@PathVariable("groupId") int gid, @PathVariable("privilege") int p){
            return groupUserService.updateGroupUserPrivilege(uid,gid,p);
        }
        //删除群用户
        @RequestMapping("/deleteGroupUser/{userId}/{groupId}")
        @ResponseBody
        public int deleteGroupUser(@PathVariable("userId") int uid,@PathVariable("groupId") int gid){
            int i = groupUserService.deleteGroupUser(uid,gid);
            groupUserService.autoInc();
            return i;
        }

        //根据群名查询群组
        @RequestMapping("/queryGroupByNameId/{groupName}")
        @ResponseBody
        public List<Group> queryGroupByName(@PathVariable("groupName") String name){
            System.out.println(groupService.queryGroupByName(name).toString());
            return groupService.queryGroupByName(name);
        }

        //根据用户名查询群用户
        @RequestMapping("/queryGroupUserByName/{userName}")
        @ResponseBody
        public List<User> queryGroupUserByName(@PathVariable("userName") String name){
            return groupUserService.queryGroupUserByName(name);
        }

        //查询项目
        @RequestMapping("/queryProject/{userId}")
        @ResponseBody
        public List<Group> queryProject(@PathVariable("userId") int uid){
            return groupUserService.queryProject(uid);
        }

        //查询群内所有用户
        @RequestMapping("/queryGroupUser/{groupId}")
        @ResponseBody
        public List<User> queryGroupUser(@PathVariable("groupId") int gid){
            return groupUserService.queryGroupUser(gid);
        }

        //查询权限
        @RequestMapping("/queryPrivilege/{userId}/{groupId}")
        @ResponseBody
        public int queryPrivilege(@PathVariable("userId") int uid,@PathVariable("groupId") int gid){
            return groupUserService.queryPrivilege(uid,gid);
        }

        //读取群消息
        @RequestMapping("/getMessage/{groupId}")
        @ResponseBody
        public String getMessage(@PathVariable("groupId") int gid){
            String ms;
            if (groupService.getMessage(gid)==null){
                ms="";
            }else{
                ms = groupService.getMessage(gid);
            }
            return ms;
        }

        //解散群组
        @RequestMapping("/deleteGroup/{groupId}")
        @ResponseBody
        public int deleteGroup(@PathVariable("groupId") int id){
            int i = groupService.deleteGroup(id);
            groupService.autoInc();
            return i;
        }
}
