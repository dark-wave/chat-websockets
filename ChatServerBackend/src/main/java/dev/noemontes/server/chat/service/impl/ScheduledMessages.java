package dev.noemontes.server.chat.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.dto.MessageDto;

@Service
public class ScheduledMessages {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@Scheduled(fixedRate = 5000)
	public void sendMessage() {
		System.out.println("Generador de mensajes: " + new Date());
		
		MessageDto message = new MessageDto();
		message.setSenderName("User sender");
		message.setReceiverName("Usuario 2");
		message.setMessage("Esto es un mensaje");
		
		message.setDate(new Date().toString());
		
		
		//simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);
		simpMessagingTemplate.convertAndSend("/chatroom/public", message);
	}
}
