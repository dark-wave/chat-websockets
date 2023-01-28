package dev.noemontes.server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/send")
	public ResponseEntity<?> sendMessage(@RequestBody MessageDto message){
		System.out.println("Mensaje en postmapping: " + message);
		messagingTemplate.convertAndSend("/topic/message", message);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@MessageMapping("/sendMessage")
	public void receiveMessage(@Payload MessageDto message) {
		System.out.println("Receive message: " + message);
		
		messagingTemplate.convertAndSend("/topic/message", message);
	}
	
	@SendTo("/topic/message")
	public MessageDto broadcastMessage(@Payload MessageDto message) {
		System.out.println("Broadcast message: " + message);
		
		return message;
	}
}
