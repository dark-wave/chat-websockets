package dev.noemontes.server.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.MessageDto;

@RestController
public class ChatController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendMessage(@RequestBody MessageDto message){
		LOG.info("PostMapping send: " + message);
		
		messagingTemplate.convertAndSend("/topic/message", message);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@MessageMapping("/sendMessage/{useruuid}")
	public void receiveMessage(@Header("simpSessionId") String sessionId, @DestinationVariable("useruuid") String useruuid, @Payload MessageDto message) {
		System.out.println("Mensaje recibido: " + message);
		System.out.println("Identificador de sesion: " + sessionId);
		System.out.println("User uuid: " + useruuid);
		
		messagingTemplate.convertAndSend("/topic/message/" + useruuid, message);
	}
	
	@SendTo("/topic/message")
	public MessageDto broadcastMessage(@Payload MessageDto message) {
		LOG.info("BroadCast message: " + message);
		return message;
	}
	
	@SendTo("/topic/message/{useruuid}")
	public MessageDto customUserMessage(@Payload MessageDto message, @DestinationVariable("useruuid") String useruuid) {
		System.out.println("Envio del mensaje al usuario: " + useruuid);
		return message;
	}
}
