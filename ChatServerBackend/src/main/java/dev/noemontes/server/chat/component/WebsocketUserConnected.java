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

	
	/**
	 * Método que comprueba si un usuario está conectado al servidor de websockets
	 * @author dark-wave
	 * @since 1.0.0
	 * @param userUuid Identificador del usuario que queremos comprobar si está conectado.
	 * @return Boolean si se encuentra o no conectado
	 */
	public boolean isUserConnecteToWebsocket(String userUuid) {
		SimpUser user = userRegistry.getUser(userUuid);
		
		return user!=null ? true : false;
	}
	
	/**
	 * Método que retorna una lista de clientes que están conectados al servidor de websockets
	 * @author dark-wave
	 * @since 1.0.0
	 * @return List<String> Listado de los usuarios conectados al socket
	 */
	public List<String> getConnectedUsers(){
		List<String> connectedUsers = new ArrayList<>();
		for (SimpUser user : userRegistry.getUsers()) {
			connectedUsers.add(user.getName());
		}
		return connectedUsers;
	}
}
