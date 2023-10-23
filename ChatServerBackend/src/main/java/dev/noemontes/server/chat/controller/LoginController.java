package dev.noemontes.server.chat.controller;

import dev.noemontes.server.chat.dto.LogoutRequestDto;
import dev.noemontes.server.chat.exceptions.LoginException;
import dev.noemontes.server.chat.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.service.LoginService;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase de servicio controladora del login de la aplicación
 */
@RestController
@RequestMapping("/login")
public class LoginController {
		
	@Autowired
	private LoginService loginService;

	@Autowired
	private ContactService contactService;

	/**
	 * Servicio de login de la aplicacion
	 * @param loginDto Contiene el email y el password del usuario que solicita login
	 * @return. ResponseEntity. Estado de la respuesta del servidor. Puede ser Status 200 OK o 401 UNAUTHORIZED
	 */
	@PostMapping()
	public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginDto) {
		UserDto logedUser = loginService.login(loginDto);
		
		if(logedUser!=null){
			return ResponseEntity.ok(logedUser);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	/**
	 * Servicio de logout de la aplicacion
	 * @param logoutRequestDto Contiene el uuid del susuario que solicita logout
	 * @return ResponseEntity. Estado de la respuesta del servidor. Puede ser Status 200 OK o 401 UNAUTHORIZED
	 */
	@PostMapping(value = "/logout")
	public ResponseEntity<?> logoutUser(@RequestBody LogoutRequestDto logoutRequestDto){
		UserDto logedUser = loginService.logout(logoutRequestDto);
		
		if(logedUser!=null) {
			contactService.notifyContacts(logedUser.getUuid());
			return ResponseEntity.ok(logedUser);
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}