package com.wetalk.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RestController;

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

    private static ConcurrentHashMap<Integer, CopyOnWriteArraySet<WebSocketServer>> map = new ConcurrentHashMap<>();
    private Session session;
    private Integer userId;
    private Integer groupId;

    @OnOpen
    public void open(@PathParam("gp_user") String gp_user, Session session){
        this.session = session;
        String[] param = gp_user.split("-");
        this.groupId = Integer.parseInt(param[0]);
        this.userId = Integer.parseInt(param[1]);
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
    public void getMessage(String message) throws IOException{
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();

        CopyOnWriteArraySet<WebSocketServer> users = map.get(groupId);
        for (WebSocketServer user : users){
            user.session.getAsyncRemote().sendText(message);
        }
    }

}
