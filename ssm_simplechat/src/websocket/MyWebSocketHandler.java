// 
// 
// 

package websocket;

import java.io.IOException;
import org.springframework.web.socket.CloseStatus;
import java.util.Iterator;
import java.util.Date;
import com.google.gson.Gson;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.TextMessage;
import com.google.gson.GsonBuilder;
import po.Message;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import service.LoginService;
import org.springframework.web.socket.WebSocketSession;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;

@Component
public class MyWebSocketHandler implements WebSocketHandler
{
    public static final Map<Long, WebSocketSession> userSocketSessionMap;
    @Autowired
    LoginService loginservice;
    
    static {
        userSocketSessionMap = new ConcurrentHashMap<Long, WebSocketSession>();
    }
    
    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
        final Long uid = (Long)session.getAttributes().get("uid");
        final String username = this.loginservice.getnamebyid(uid);
        if (MyWebSocketHandler.userSocketSessionMap.get(uid) == null) {
            MyWebSocketHandler.userSocketSessionMap.put(uid, session);
            final Message msg = new Message();
            msg.setFrom(0L);
            msg.setText(username);
            this.broadcast(new TextMessage((CharSequence)new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson((Object)msg)));
        }
    }
    
    public void handleMessage(final WebSocketSession session, final WebSocketMessage<?> message) throws Exception {
        if (message.getPayloadLength() == 0) {
            return;
        }
        final Message msg = (Message)new Gson().fromJson(message.getPayload().toString(), (Class)Message.class);
        msg.setDate(new Date());
        this.sendMessageToUser(msg.getTo(), new TextMessage((CharSequence)new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson((Object)msg)));
    }
    
    public void handleTransportError(final WebSocketSession session, final Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        for (final Map.Entry<Long, WebSocketSession> entry : MyWebSocketHandler.userSocketSessionMap.entrySet()) {
            if (entry.getValue().getId().equals(session.getId())) {
                MyWebSocketHandler.userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket\u4f1a\u8bdd\u5df2\u7ecf\u79fb\u9664:\u7528\u6237ID" + entry.getKey());
                final String username = this.loginservice.getnamebyid(entry.getKey());
                final Message msg = new Message();
                msg.setFrom(-2L);
                msg.setText(username);
                this.broadcast(new TextMessage((CharSequence)new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson((Object)msg)));
                break;
            }
        }
    }
    
    public void afterConnectionClosed(final WebSocketSession session, final CloseStatus closeStatus) throws Exception {
        System.out.println("Websocket:" + session.getId() + "\u5df2\u7ecf\u5173\u95ed");
        for (final Map.Entry<Long, WebSocketSession> entry : MyWebSocketHandler.userSocketSessionMap.entrySet()) {
            if (entry.getValue().getId().equals(session.getId())) {
                MyWebSocketHandler.userSocketSessionMap.remove(entry.getKey());
                System.out.println("Socket\u4f1a\u8bdd\u5df2\u7ecf\u79fb\u9664:\u7528\u6237ID" + entry.getKey());
                final String username = this.loginservice.getnamebyid(entry.getKey());
                final Message msg = new Message();
                msg.setFrom(-2L);
                msg.setText(username);
                this.broadcast(new TextMessage((CharSequence)new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson((Object)msg)));
                break;
            }
        }
    }
    
    public boolean supportsPartialMessages() {
        return false;
    }
    
    public void broadcast(final TextMessage message) throws IOException {
        for (final Map.Entry<Long, WebSocketSession> entry : MyWebSocketHandler.userSocketSessionMap.entrySet()) {
            if (entry.getValue().isOpen()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (entry.getValue().isOpen()) {
                                entry.getValue().sendMessage((WebSocketMessage)message);
                            }
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }
    
    public void sendMessageToUser(final Long uid, final TextMessage message) throws IOException {
        final WebSocketSession session = MyWebSocketHandler.userSocketSessionMap.get(uid);
        if (session != null && session.isOpen()) {
            session.sendMessage((WebSocketMessage)message);
        }
    }
}
