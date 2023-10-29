package dev.noemontes.server.chat.service;

import java.util.List;

import dev.noemontes.server.chat.dto.UserRegisterDto;
import dev.noemontes.server.chat.exceptions.UserExistsException;
import dev.noemontes.server.chat.exceptions.UserNotCreateException;

public interface UserService {
	public UserRegisterDto saveUser(UserRegisterDto userDto) throws UserNotCreateException, UserExistsException;
	public List<UserRegisterDto> listUsers();
	public UserRegisterDto addContactToUser(String userId, String contactId);
	public UserRegisterDto getUserById(String id);
	public List<UserRegisterDto> getUserContacts(String useruuid);
	
	/**
	 * @author dark-wave
	 * Método que actualiza el sessionID generado en la conexion con websocket a un usuario+
	 * mediante su useruuid
	 * 
	 * @param user
	 * @param sessionId
	 */
	public void updateUserSessionId(String userUuid, String sessionId);
	
	
	/**
	 * @author dark-wave
	 * Método que elimina el sessionId de un usuario
	 * @param sessionId
	 */
	public void removeUserSessionId(String sessionId);

	/**
	 * @autor dark-wave
	 * Método que elimina un contacto de un usuario.
	 * @param userUuid
	 * @param contactUuid
	 */
	public void removeContact(String userUuid, String contactUuid);
}
