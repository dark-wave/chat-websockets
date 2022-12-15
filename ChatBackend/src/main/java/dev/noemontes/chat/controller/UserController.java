package dev.noemontes.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.chat.dto.UserDto;
import dev.noemontes.chat.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/getuuid")
	public String getUuid() {
		return "Prueba de generacion de uuid";
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto){
		UserDto userDtoServiceResponse = userService.saveUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoServiceResponse);
	}
}
