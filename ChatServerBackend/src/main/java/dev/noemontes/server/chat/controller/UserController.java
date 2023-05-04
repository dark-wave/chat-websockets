package dev.noemontes.server.chat.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.ContactRequestDto;
import dev.noemontes.server.chat.dto.ContactResponseDto;
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
	
	@PutMapping("/addContact/{userId}/{contactId}")
	public ResponseEntity<?> addContactToUser(@PathVariable String userId, @PathVariable String contactId){
		UserRegisterDto userDto = userService.addContactToUser(userId, contactId);
		
		if(userDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(userDto);
		
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteAllUsers(){
		userService.deleteAllUsers();
		
		return ResponseEntity.noContent().build();
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
	public ResponseEntity<?> getUserById(@PathVariable String id){
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

		if (listUserDb == null || listUserDb.isEmpty()) {
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
			contactRequestDto.setResponseUserEmail(dbContactUser.getEmail());
			
			messagingTemplate.convertAndSendToUser(dbContactUser.getUuid(), "/events/event", contactRequestDto);
			
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/contact/response")
	public ResponseEntity<?> contactResponse(@RequestBody ContactResponseDto contactResponseDto){
		// Comprobamos que el usuario que ha iniciado la petición no ha eliminado su cuenta entre la peticion y la respuesta
		Optional<UserEntity> requestContactUser = userService.getUserByUuid(contactResponseDto.getContactRequest().getRequestUserUuid());
		
		if(requestContactUser.isPresent()) {
			UserEntity userRequestDb = requestContactUser.get();
			
			if(contactResponseDto.getContactAccepted()) {
				Optional<UserEntity> opContactUserDb = userService.getUserByUuid(contactResponseDto.getUserUuidResponse());
				//Si el contacto acepta generamos la relacción
				if(opContactUserDb.isPresent()) {
					UserEntity contactUserDb = opContactUserDb.get();
					
					//Añadimos el contacto a la lista de contados del usuario que inició la petidicón y lo guardamos en base de datos
					userRequestDb.getContacts().add(contactUserDb);
					
					UserRegisterDto userDtoServiceResponse = userService.saveUser(userRequestDb);
					
					return ResponseEntity.status(HttpStatus.OK).body(userDtoServiceResponse);
				}
			}
		}
		
		return ResponseEntity.notFound().build();
	}
}