package com.wetalk.controller;

import com.wetalk.dao.GroupMapper;
import com.wetalk.dao.GroupUserMapper;
import com.wetalk.service.GroupService;
import com.wetalk.service.GroupUserService;
import com.wetalk.service.GroupUserServiceImpl;
import com.wetalk.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/groupchat/{gp_user}")
@RestController
public class WebSocketServer {

//    private static final User_MessageService service;
//
//    static {
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//        service = ((User_MessageService) ctx.getBean("user_MessageServiceImpl"));
//    }


    public static WebSocketServer webSocketServer;

    @Autowired
    GroupService groupService;

    @Autowired
    GroupUserService groupUserService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private String ms;
    //@Autowired
    //RedisTemplate<String, String> redisTemplate;

    @PostConstruct
    public void init(){
        webSocketServer = this;
        webSocketServer.groupService = this.groupService;
        webSocketServer.groupUserService = this.groupUserService;
        webSocketServer.redisUtil = this.redisUtil;
        webSocketServer.redisTemplate = this.redisTemplate;
    }



    private static ConcurrentHashMap<Integer, CopyOnWriteArraySet<WebSocketServer>> map = new ConcurrentHashMap<>();
    private Session session;
    private Integer userId;
    private Integer groupId;
    private String userName;

    @OnOpen
    public void open(@PathParam("gp_user") String gp_user, Session session){
        this.session = session;
        String[] param = gp_user.split("-");
        this.groupId = Integer.parseInt(param[0]);
        this.userId = Integer.parseInt(param[1]);
        this.userName = webSocketServer.groupUserService.queryGroupUserNameById(userId);
        this.ms = webSocketServer.groupService.getMessage(groupId);
        CopyOnWriteArraySet<WebSocketServer> users = map.get(groupId);
        if (users == null){
            synchronized (map){
                if(!map.containsKey(groupId)){
                    users = new CopyOnWriteArraySet<>();
                    map.put(groupId,users);
                }
            }
        }
        users.add(this);
    }

    @OnClose
    public void close(){
        CopyOnWriteArraySet<WebSocketServer> users = map.get(groupId);
        if(users != null){
            users.remove(this);
        }
    }

    @OnError
    public void error(Throwable error){
        error.printStackTrace();
    }

    @OnMessage
    public void getMessage(String message){
        CopyOnWriteArraySet<WebSocketServer> users = map.get(groupId);
        System.out.println(ms);
        ms += message+"\n";
        webSocketServer.redisTemplate.opsForValue().set(groupId.toString(),ms);
        for (WebSocketServer user : users){
            user.session.getAsyncRemote().sendText(message);
        }
    }
}
