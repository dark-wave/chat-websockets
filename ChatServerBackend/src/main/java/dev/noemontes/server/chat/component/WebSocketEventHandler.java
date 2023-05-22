package dev.noemontes.server.chat.component;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dark-wave
 * @since 1.0.0
 * Clase componente que controla el estado de las conexiones WebSocket
 * cuando un cliente se conecta o se desconecta.
 */
@Component
public class WebSocketEventHandler extends AbstractWebSocketHandler {
    private List<WebSocketSessionInfo> connectedUsersList = new ArrayList<WebSocketSessionInfo>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String sessionId = session.getId();
        String username = session.getPrincipal().getName();

        WebSocketSessionInfo sessionInfo = new WebSocketSessionInfo(sessionId, username);
        connectedUsersList.add(sessionInfo);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String sessionId = session.getId();
        connectedUsersList.removeIf(sessionInfo -> sessionInfo.getSessionId().equals(sessionId));
    }
}
