package dev.noemontes.server.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase para testear la disponibiliad del servidor.
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@GetMapping("/")
	public ResponseEntity<?> testConnection(){
		return ResponseEntity.ok("Respuesta de servidor correcta");
	}
}
