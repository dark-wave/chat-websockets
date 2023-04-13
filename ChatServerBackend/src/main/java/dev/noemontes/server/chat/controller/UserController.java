package dev.noemontes.server.chat.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.ContactRequestDto;
import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.entity.UserEntity;
import dev.noemontes.server.chat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;
	
	@PostMapping("/create")
	public ResponseEntity<?> saveUser(@RequestBody UserRegisterDto userDto){
		UserRegisterDto userDtoServiceResponse = userService.saveUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoServiceResponse);
	}
	
	@GetMapping("/list")
	public ResponseEntity<?> listUsers(){
		List<UserRegisterDto> listUserDb = userService.listUsers();
		
		if(listUserDb.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(listUserDb);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable Long id){
		UserRegisterDto user = userService.getUserById(id);
		
		if(user!=null) {
			return ResponseEntity.ok(user);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/contacts/{useruuid}")
	public ResponseEntity<?> getUserContacts(@PathVariable String useruuid) {
		List<UserRegisterDto> listUserDb = userService.getUserContacts(useruuid);

		if (listUserDb.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(listUserDb);
		}
	}

	@PostMapping("/contacts/request")
	public ResponseEntity<?> contactRequest(@RequestBody ContactRequestDto contactRequestDto) {
		Optional<UserEntity> contactUser = userService.getUserByEmail(contactRequestDto.getRequestUserEmail());
		
		if(contactUser.isPresent()) {
			UserEntity dbContactUser = contactUser.get();
			
			contactRequestDto.setResponseUserUuid(dbContactUser.getUuid());
			contactRequestDto.setResponseUserName(dbContactUser.getName());
			contactRequestDto.setResponseUserEmail(dbContactUser.getEmail());
			
			messagingTemplate.convertAndSendToUser(contactRequestDto.getResponseUserUuid(), "/events/event", contactRequestDto);
			
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/contact/response")
	public ResponseEntity<?> contactResponse(@RequestBody ContactRequestDto contactRequestDto){
		// Comprobamos que el usuario que ha iniciado la petición no ha eliminado su cuenta entre la peticion y la respuesta
		Optional<UserEntity> requestContactUser = userService.getUserByUuid(contactRequestDto.getRequestUserUuid());
		
		if(requestContactUser.isPresent()) {
			UserEntity userRequestDb = requestContactUser.get();
			
		}
		
		return ResponseEntity.notFound().build();
	}
}