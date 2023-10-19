package dev.noemontes.server.chat.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import dev.noemontes.server.chat.exceptions.ContactExistsException;
import dev.noemontes.server.chat.exceptions.UserNotFoundException;
import dev.noemontes.server.chat.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.bind.annotation.*;

import dev.noemontes.server.chat.dto.ContactRequestDto;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase controladora de eventos de contacto
 */
@RestController
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService contactService;

	 /**
	  * Método para solicitar a un contacto que se añada a la lista de contactos. Se envía un mensaje usando
	  * websockets para que al usuario destino le apareza una notificación de que tiene una petición de contacto.
	  * @param contactRequestDto
	  * @return ResponseEntity
	 */
	@PostMapping("/request")
	public ResponseEntity<?> contactRequest(@RequestBody ContactRequestDto contactRequestDto) {
		try{
			contactService.addContact(contactRequestDto);
			return ResponseEntity.ok().build();
		} catch (UserNotFoundException ex) { //El usuario no existe
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.NO_CONTENT);
		} catch (ContactExistsException ex){//El usuario ya es contacto
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
		}
	}

	@GetMapping("/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("Prueba correcta");
	}
}
