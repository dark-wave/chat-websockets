package dev.noemontes.server.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserLoginResponseDto;
import dev.noemontes.server.chat.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
		
	@Autowired
	private LoginService loginService;
	
	@PostMapping
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginDto){
		UserLoginResponseDto logedUser = loginService.login(loginDto);
		
		if(logedUser!=null) {	
			return ResponseEntity.ok(logedUser);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
