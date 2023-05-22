package dev.noemontes.server.chat.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author dark-wave
 * @since 1.0.0
 * Clase que controla si un indentificador de usuario está conectado
 * al websocket o no.
 */
@Component
public class WebsocketUserConnected {
	
	@Autowired
	private SimpUserRegistry userRegistry;


	public boolean isUserConnecteToWebsocket(String userUuid) {
		SimpUser user = userRegistry.getUser(userUuid);
		
		return user!=null ? true : false;
	}

	public List<String> getConnectedUsers(){
		List<String> connectedUsers = new ArrayList<>();
		for (SimpUser user : userRegistry.getUsers()) {
			connectedUsers.add(user.getName());
		}
		return connectedUsers;
	}
}
