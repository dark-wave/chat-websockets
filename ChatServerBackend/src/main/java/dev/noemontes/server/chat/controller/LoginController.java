package dev.noemontes.server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.LoginDto;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
		
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
		UserDto loginUser = new UserDto();
		loginUser.setEmail(loginDto.getEmail());
		loginUser.setPassword(loginDto.getPassword());
		
		UserDto logedUser = loginService.login(loginUser);
		
		if(logedUser!=null) {
			logedUser.setOnline(true);
			
			return ResponseEntity.ok(logedUser);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
