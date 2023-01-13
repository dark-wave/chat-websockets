package dev.noemontes.server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import dev.noemontes.server.chat.dto.MessageDto;

/**
 * Controlador de mensajes de chat
 * 
 * @author nmontes
 * @since 1.0.0
 */
@Controller
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@MessageMapping("/message")
	@SendTo("/chatroom/public")
	public MessageDto receiveMessage(@Payload MessageDto message) {
		System.out.println();
		return message;
	}
	
	@MessageMapping("/private-message")
	public MessageDto recMessage(@Payload MessageDto message) {
		simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
		System.out.println(message.toString());
		return message;
	}
}
