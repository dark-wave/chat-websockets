package dev.noemontes.server.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	/**
	 * Método para solicitar a un contacto que se añada a la lista de contactos. Se envía un mensaje usando
	 * websockets para que al usuario destino le apareza una notificación de que tiene una petición de contacto.
	 * @param contactRequestDto Objeto que contiene los datos del usuario que realiza la petición y el email
	 * del usuario que recibe la petición.
	 * @return ResponseEntity. Estado de la respuesta del servidor. Se controla si el usuario destino
	 * está en la base de datos. Si no está se devuelve un HttpStatus.notFound. Si existe se devuelve un HttpStatus.ok
	 * y se envia un mensaje usando websockets al usuario destino.
	 */
	@PostMapping("/request")
	public ResponseEntity<?> contactRequest(@RequestBody ContactRequestDto contactRequestDto) {
		//TODO: Implementar la lógica de solicitud de contacto contra la base de datos MongoDb
		return ResponseEntity.notFound().build();
	}

	/**
	 * Servicio que recibe la respuesta del usuario a una solicitud de contacto
	 * @param contactResponseDto Objeto que contiene la respuesta de la petición
	 * @return ResponseEntity Estado de la respuesta del servidor.
	 */
	@PostMapping("/response")
	public ResponseEntity<?> contactResponse(@RequestBody ContactRequestDto contactResponseDto){
		//TODO: Implementar la lógica de respuesta de contacto contra la base de datos MongoDb
		return ResponseEntity.notFound().build();
	}
}
