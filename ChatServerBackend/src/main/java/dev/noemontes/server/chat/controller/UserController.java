package dev.noemontes.server.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
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

	@GetMapping("/contacts/{userUuid}/{emailContact}")
	public void contactRequest(@PathVariable String useruuid, @PathVariable String emailContact) {

	}
}