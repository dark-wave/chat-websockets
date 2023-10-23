package dev.noemontes.server.chat.controller;

import dev.noemontes.server.chat.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.service.UserService;

/**
 * 
 * @author dark-wave
 * @since 1.0.0
 *
 */
@RestController
public class SocketController {
	@Autowired
	private UserService userService;

	@Autowired
	private ContactService contactService;
	
	@MessageMapping("/connect")
	public void connectUserToSession(@Header("simpSessionId") String sessionId,  @Payload String userUuid) {
		userService.updateUserSessionId(userUuid, sessionId);

		//Publicamos los contactos del usuario en la cola en la carga incial
		contactService.loadContacts(userUuid);

		//Notificamos a los contactos que nos hemos conectado
		contactService.notifyContacts(userUuid);
	}	
}
