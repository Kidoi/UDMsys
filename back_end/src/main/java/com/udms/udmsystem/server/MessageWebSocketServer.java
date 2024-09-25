package com.udms.udmsystem.server;

import com.alibaba.fastjson.JSONObject;
import com.udms.udmsystem.pojo.DeviceVO;
import com.udms.udmsystem.service.DeviceService;
import com.udms.udmsystem.service.UserMessageService;
import com.udms.udmsystem.util.ApplicationContextRegister;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/websocket/message/{sid}")
@Component
@Slf4j
public class MessageWebSocketServer {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<MessageWebSocketServer> webSocketSet = new CopyOnWriteArraySet<MessageWebSocketServer>();

    private static ConcurrentHashMap<Integer, Timer> timerMap = new ConcurrentHashMap<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    //接收sid
    private Integer sid;
   

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("sid") Integer sid
    ) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            log.error("websocket IO异常");
        }


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            ApplicationContext act = ApplicationContextRegister.getApplicationContext();
            UserMessageService userMessageService = act.getBean(UserMessageService.class);
            @Override
            public void run() {
                Boolean res = userMessageService.hasMessage(sid);
                try {
                    MessageWebSocketServer.sendInfo(JSONObject.toJSONString(res) , sid);
                } catch (IOException e) {
                    timer.cancel();
                    throw new RuntimeException(e);
                }
            }
        }, 0L, 1000L);
        timerMap.put(sid, timer);
    }

    @OnClose
    public void onClose() {
        timerMap.get(this.sid).cancel();
        timerMap.remove(this.sid);
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (MessageWebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message, @PathParam("sid") Integer sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (MessageWebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(item.session.isOpen()){
                    if(sid==null) {
                        item.sendMessage(message);
                    }else if(item.sid.equals(sid)){
                        item.sendMessage(message);
                    }
                }
            } catch (IOException e) {
                continue;
            }
        }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
    public static synchronized void addOnlineCount() {
        MessageWebSocketServer.onlineCount++;
    }
    public static synchronized void subOnlineCount() {
        MessageWebSocketServer.onlineCount--;
    }
    public static CopyOnWriteArraySet<MessageWebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}