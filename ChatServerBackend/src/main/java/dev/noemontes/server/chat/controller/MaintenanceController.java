package dev.noemontes.server.chat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.service.UserService;

/**
 * @author dark-wave
 * @since 1.0.0
 * 
 * Clase controladora para utilizar en modo de mantenimiento y en fase
 * de desarrollo.
 *
 */
@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
	private static final Logger LOG = LoggerFactory.getLogger(MaintenanceController.class);

	@Autowired
	private UserService userService;
	
	/**
	 * Método para añadir un contacto a un usuario
	 * @param userId Id de usuario que solicita la peticion de contacto
	 * @param contactId Id de usuario que recibe la peticion de contacto
	 * @return ResponseEntity. Estado de la respuesta del servidor
	 */
	@PutMapping("/user/addContact/{userId}/{contactId}")
	public ResponseEntity<?> addContactToUser(@PathVariable String userId, @PathVariable String contactId){
		UserRegisterDto userDto = userService.addContactToUser(userId, contactId);
		
		if(userDto == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(userDto);
	}
	
	/**
	 * Método que lista todos los usuarios contendios en la base de datos. No tiene funcionalidad
	 * relacionada con el chat. Usando en el proceso de desarrollo de la aplicación
	 * @return ResponseEntity. Estado de la respuesta del servidor, que contiene
	 * ademas la lista de usuarios de la base de datos
	 */
	@GetMapping("/user/list")
	public ResponseEntity<?> listUsers(){
		//TODO: Cambiar la forma de obtener los contactos para que se actualice el estado cuando se conecten
		List<UserRegisterDto> listUserDb = userService.listUsers();
		
		if(listUserDb.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(listUserDb);
		}
	}
	
	/**
	 * Método para obtener un usuario por su id
	 * @param id Id del usuario a obtener
	 * @return ResponseEntity. Estado de la respuesta del servidor, que contiene el
	 * objeto dto que contiene el usuario solicitado o un HttpStatus.notFound si no se encuentra.
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<?> getUserById(@PathVariable String id){
		UserRegisterDto user = userService.getUserById(id);
		
		if(user!=null) {
			return ResponseEntity.ok(user);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * Método para obtener los contactos que tiene un usuario dado por su uuid
	 * @param useruuid Identificador unico del usuario
	 * @return HttpStatus. Estado de la respuesta del servidor, que contiene la lista de contactos
	 */
	@GetMapping("/user/contacts/{useruuid}")
	public ResponseEntity<?> getUserContacts(@PathVariable String useruuid) {
		List<UserRegisterDto> listUserDb = userService.getUserContacts(useruuid);

		if (listUserDb == null || listUserDb.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(listUserDb);
		}
	}
	
	
	/**
	 * Método para agregar un usuario a otro como contacto
	 *
	 * @param useruuid Identificador unico del usuario que solicita la peticion de contacto
	 * @param contactuuid Identificador unico del usuario que recibe la peticion de contacto
	 */
	@PutMapping("/user/contacts/{useruuid}/{contactuuid}")
	public ResponseEntity<?> addContact(@PathVariable String useruuid, @PathVariable String contactuuid) {
		UserRegisterDto userDto = userService.addContactToUser(useruuid, contactuuid);

		if (userDto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(userDto);
	}

	/**
	 * Método para eliminar un contacto de un usuario y actualiza la base de datos.
	 * @param userUuid Identificador unico del usuario sobre el que se borrara el contacto
	 * @param contactUuid Identificador unico del contacto que se va desasociar
	 * @return HttpStatus. Estado de la respuesta del servidor.
	 */
	@DeleteMapping("/user/contacts/{useruuid}/{contactuuid}")
	public ResponseEntity<?> deleteContact(@PathVariable("useruuid") String userUuid, @PathVariable("contactuuid") String contactUuid){
		userService.removeContact(userUuid, contactUuid);

		return ResponseEntity.notFound().build();
	}
}