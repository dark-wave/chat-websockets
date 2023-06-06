package dev.noemontes.server.chat.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.dto.MessageResponseDto;
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
	
	@GetMapping("/test")
	public String testConection() {
		return "Test de servicio de mensajes ok: " + new Date();
	}

	@MessageMapping("/sendMessage/{useruuid}")
	public void receiveMessage(@Header("simpSessionId") String sessionId, @DestinationVariable("useruuid") String useruuid, @Payload MessageRequestDto message) {
		MessageRequestDto messageRequestDto = new MessageRequestDto();
		messageRequestDto.setUuidSender(message.getUuidSender());
		messageRequestDto.setUuidReceiver(useruuid);
		messageRequestDto.setMessage(message.getMessage());
		
		messageService.saveMessage(messageRequestDto);

		messagingTemplate.convertAndSendToUser(useruuid, "/queue/messages", message);
	}
	
	@GetMapping("/{uuidSender}/{uuidReceiver}")
	public ResponseEntity<?> getLastMessages(@PathVariable String uuidSender, @PathVariable String uuidReceiver){
		List<MessageResponseDto> messageList = messageService.getLastMessages(uuidSender, uuidReceiver);
		
		if(messageList == null || messageList.size() == 0) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(messageList);
	}
	
	@PostMapping
	public ResponseEntity<?> saveMessage(@RequestBody MessageRequestDto message){
		MessageResponseDto messageSaveResponseDto = messageService.saveMessage(message);
		
		if(messageSaveResponseDto!=null) {
			return ResponseEntity.ok(messageSaveResponseDto);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping
	public ResponseEntity<?> getMessageList(){
		List<MessageResponseDto> messageList = messageService.getMessages();
		
		if(messageList.size()>0) {
			return ResponseEntity.ok(messageList);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAllMessages(){
		messageService.deleteAllMessages();
		
		return ResponseEntity.noContent().build();
	}
}
