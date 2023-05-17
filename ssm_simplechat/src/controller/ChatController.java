// 
// 
// 

package controller;

import po.User;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import org.springframework.web.socket.TextMessage;
import com.google.gson.GsonBuilder;
import java.util.Date;
import po.Message;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Iterator;
import org.springframework.web.socket.WebSocketSession;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpSession;
import service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import websocket.MyWebSocketHandler;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController
{
    @Autowired
    MyWebSocketHandler handler;
    @Autowired
    LoginService loginservice;
    
    @RequestMapping({ "/onlineusers" })
    @ResponseBody
    public Set<String> onlineusers(final HttpSession session) {
        final Map<Long, WebSocketSession> map = MyWebSocketHandler.userSocketSessionMap;
        final Set<Long> set = map.keySet();
        final Iterator<Long> it = set.iterator();
        final Set<String> nameset = new HashSet<String>();
        while (it.hasNext()) {
            final Long entry = it.next();
            final String name = this.loginservice.getnamebyid(entry);
            final String user = (String)session.getAttribute("username");
            if (!user.equals(name)) {
                nameset.add(name);
            }
        }
        return nameset;
    }
    
    @ResponseBody
    @RequestMapping(value = { "broadcast" }, method = { RequestMethod.POST })
    public void broadcast(@RequestParam("text") final String text) throws IOException {
        final Message msg = new Message();
        msg.setDate(new Date());
        msg.setFrom(-1L);
        msg.setFromName("\u7cfb\u7edf\u5e7f\u64ad");
        msg.setTo(0L);
        msg.setText(text);
        this.handler.broadcast(new TextMessage((CharSequence)new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson((Object)msg)));
    }
    
    @RequestMapping({ "getuid" })
    @ResponseBody
    public User getuid(@RequestParam("username") final String username) {
        final Long a = this.loginservice.getUidbyname(username);
        final User u = new User();
        u.setUid(a);
        return u;
    }
}
