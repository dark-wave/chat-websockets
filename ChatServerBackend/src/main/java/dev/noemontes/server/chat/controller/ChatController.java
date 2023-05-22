package dev.noemontes.server.chat.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.service.MessageService;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase controladora de mensajes del chat
 */
@RestController
public class ChatController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ChatController.class);
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private MessageService messageService;

	@MessageMapping("/sendMessage/{useruuid}")
	public void receiveMessage(@Header("simpSessionId") String sessionId, @DestinationVariable("useruuid") String useruuid, @Payload MessageRequestDto message) {
		MessageRequestDto messageRequestDto = new MessageRequestDto();
		messageRequestDto.setUidSender(message.getUidSender());
		messageRequestDto.setUidReceiver(useruuid);
		messageRequestDto.setMessage(message.getMessage());
		
		messageService.saveMessage(messageRequestDto);

		messagingTemplate.convertAndSendToUser(useruuid, "/queue/messages", message);
	}

	@PostMapping("/newMessage")
	public ResponseEntity<?> sendMessage(@RequestBody MessageRequestDto message) {
		messagingTemplate.convertAndSendToUser(message.getUidReceiver(), "/queue/messages", message);
		return ResponseEntity.ok().build();
	}
}
