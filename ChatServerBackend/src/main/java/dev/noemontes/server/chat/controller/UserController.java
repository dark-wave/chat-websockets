package dev.noemontes.server.chat.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.noemontes.server.chat.component.WebsocketUserConnected;
import dev.noemontes.server.chat.dto.ContactRequestDto;
import dev.noemontes.server.chat.dto.ContactResponseDto;
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
	
	@Autowired
	SimpMessagingTemplate messagingTemplate;

	@Autowired
	WebsocketUserConnected websocketUserConnected;

	@GetMapping("/connected")
	public ResponseEntity<?> getConnectedUsers(){
		List<String> connectedUsers = websocketUserConnected.getConnectedUsers();

		return ResponseEntity.ok(connectedUsers);
	}
	/**
	 * Metodo para crear un usuario en el sistema
	 * @param userDto
	 * @return ResponseEntity. Estado de la respuesta del servidor
	 */
	@PostMapping("/create")
	public ResponseEntity<?> saveUser(@RequestBody UserRegisterDto userDto){
		UserRegisterDto userDtoServiceResponse = userService.saveUser(userDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userDtoServiceResponse);
	}

	/**
	 * Método para añadir un contacto a un usuario
	 * @param userId Id de usuario que solicita la peticion de contacto
	 * @param contactId Id de usuario que recibe la peticion de contacto
	 * @return ResponseEntity. Estado de la respuesta del servidor
	 */
	@PutMapping("/addContact/{userId}/{contactId}")
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
	@GetMapping("/list")
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
	@GetMapping("/{id}")
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
	@GetMapping("/contacts/{useruuid}")
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
	@PutMapping("/contacts/{useruuid}/{contactuuid}")
	public ResponseEntity<?> addContact(@PathVariable String useruuid, @PathVariable String contactuuid) {
		UserRegisterDto userDto = userService.addContactToUser(useruuid, contactuuid);

		if (userDto == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(userDto);
	}

	/**
	 * Método para solicitar a un contacto que se añada a la lista de contactos. Se envía un mensaje usando
	 * websockets para que al usuario destino le apareza una notificación de que tiene una petición de contacto.
	 * @param contactRequestDto Objeto que contiene los datos del usuario que realiza la petición y el email
	 * del usuario que recibe la petición.
	 * @return ResponseEntity. Estado de la respuesta del servidor. Se controla si el usuario destino
	 * está en la base de datos. Si no está se devuelve un HttpStatus.notFound. Si existe se devuelve un HttpStatus.ok
	 * y se envia un mensaje usando websockets al usuario destino.
	 */
	@PostMapping("/contacts/request")
	public ResponseEntity<?> contactRequest(@RequestBody ContactRequestDto contactRequestDto) {
		//TODO: Implementar la lógica de solicitud de contacto contra la base de datos MongoDb
		return ResponseEntity.notFound().build();
	}

	/**
	 * Servicio que recibe la respuesta del usuario a una solicitud de contacto
	 * @param contactResponseDto Objeto que contiene la respuesta de la petición
	 * @return ResponseEntity Estado de la respuesta del servidor.
	 */
	@PostMapping("/contact/response")
	public ResponseEntity<?> contactResponse(@RequestBody ContactResponseDto contactResponseDto){
		//TODO: Implementar la lógica de respuesta de contacto contra la base de datos MongoDb
		return ResponseEntity.notFound().build();
	}
	
	/**
	 * Metodo para eliminar todos los usuarios del sistema. No tiene funcionalidad relacionada con el chat.
	 * Se utiliza para facilitar las pruebas
	 * @return ResponseEntity. Estado de la respuesta del servidor
	 */
	@DeleteMapping
	public ResponseEntity<?> deleteAllUsers(){
		userService.deleteAllUsers();
		
		return ResponseEntity.noContent().build();
	}
}