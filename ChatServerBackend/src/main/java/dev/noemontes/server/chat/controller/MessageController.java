package dev.noemontes.server.chat.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.MessageRequestDto;
import dev.noemontes.server.chat.dto.MessageResponseDto;
import dev.noemontes.server.chat.service.MessageService;

@RestController
@RequestMapping(path = "/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@GetMapping("/test")
	public String testConection() {
		return "Test de servicio de mensajes ok: " + new Date();
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
}
