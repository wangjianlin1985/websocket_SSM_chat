// 
// 
// 

package websocket;

import javax.servlet.http.HttpSession;
import org.springframework.http.server.ServletServerHttpRequest;
import java.util.Map;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.server.HandshakeInterceptor;

public class HandShake implements HandshakeInterceptor
{
    public boolean beforeHandshake(final ServerHttpRequest request, final ServerHttpResponse response, final WebSocketHandler wsHandler, final Map<String, Object> attributes) throws Exception {
        System.out.println("Websocket:\u7528\u6237[ID:" + ((ServletServerHttpRequest)request).getServletRequest().getSession(false).getAttribute("uid") + "]\u5df2\u7ecf\u5efa\u7acb\u8fde\u63a5");
        if (request instanceof ServletServerHttpRequest) {
            final ServletServerHttpRequest servletRequest = (ServletServerHttpRequest)request;
            final HttpSession session = servletRequest.getServletRequest().getSession(false);
            final Long uid = (Long)session.getAttribute("uid");
            if (uid == null) {
                return false;
            }
            attributes.put("uid", uid);
        }
        return true;
    }
    
    public void afterHandshake(final ServerHttpRequest request, final ServerHttpResponse response, final WebSocketHandler wsHandler, final Exception exception) {
        System.out.println("after hand");
    }
}
