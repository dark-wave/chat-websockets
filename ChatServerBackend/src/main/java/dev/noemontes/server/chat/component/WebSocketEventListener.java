package dev.noemontes.server.chat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import dev.noemontes.server.chat.service.UserService;

/**
 * @author dark-wave
 * @since 1.0.0
 * 
 * Clase que escucha conexiones y desconexiones de clientes Stomp al
 * servidor de websockets
 */
@Component
public class WebSocketEventListener {
	
	@Autowired
	private UserService userService;
	
	@EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
               
        // Acciones cuando un cliente se conecta
        System.out.println("Cliente conectado. ID de sesión: " + sessionId);
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String sessionId = accessor.getSessionId();
        
        userService.removeUserSessionId(sessionId);
        
        // Acciones cuando un cliente se desconecta
        System.out.println("Cliente desconectado. ID de sesión: " + sessionId);
    }
}
