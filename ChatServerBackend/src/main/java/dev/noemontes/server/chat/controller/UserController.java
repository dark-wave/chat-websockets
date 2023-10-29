package dev.noemontes.server.chat.controller;

import dev.noemontes.server.chat.exceptions.UserExistsException;
import dev.noemontes.server.chat.exceptions.UserNotCreateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.service.UserService;

/**
 * @author dark-wave
 * @since 1.0.0
 *
 * Clase de servicios de usuarios para gestionar el crud de los mismos y exponerlos
 * como servicio rest.
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	/**
	 * Metodo para crear un usuario en el sistema
	 * @param userDto
	 * @return ResponseEntity. Estado de la respuesta del servidor
	 */
	@PostMapping("/create")
	public ResponseEntity<?> saveUser(@RequestBody UserRegisterDto userDto){

		try{
			UserRegisterDto userDtoServiceResponse = userService.saveUser(userDto);
			return ResponseEntity.status(HttpStatus.CREATED).body(userDtoServiceResponse);
		}catch (UserNotCreateException ex){
			LOG.error("Error al crear el usuario: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
		}catch (UserExistsException ex){
			LOG.error("Error al crear el usuario: " + ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
}