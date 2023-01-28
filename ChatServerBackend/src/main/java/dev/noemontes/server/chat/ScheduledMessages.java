package dev.noemontes.server.chat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dev.noemontes.server.chat.dto.MessageDto;

@Component
public class ScheduledMessages {
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Scheduled(fixedDelay = 2000)
	public void sendMessage() {
		MessageDto message = new MessageDto();
		message.setUidSender("spring_sender");
		message.setUidReceiver("flutter_receiver");
		message.setMessage("Mensaje automatico: " + new Date());
		
		System.out.println(message);
		
		messagingTemplate.convertAndSend("/topic/message", message);
	}
}
