// 
// 
// 

package websocket;

import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import javax.annotation.Resource;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer
{
    @Resource
    MyWebSocketHandler handler;
    
    public void registerWebSocketHandlers(final WebSocketHandlerRegistry registry) {
        registry.addHandler((WebSocketHandler)this.handler, new String[] { "/ws" }).addInterceptors(new HandshakeInterceptor[] { new HandShake() });
        registry.addHandler((WebSocketHandler)this.handler, new String[] { "/ws/sockjs" }).addInterceptors(new HandshakeInterceptor[] { new HandShake() }).withSockJS();
    }
}
