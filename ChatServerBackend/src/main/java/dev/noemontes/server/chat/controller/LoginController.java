package dev.noemontes.server.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.LoginDto;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@PostMapping
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
		return ResponseEntity.ok(loginDto);
	}
}
