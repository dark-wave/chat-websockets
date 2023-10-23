package dev.noemontes.server.chat.service.impl;

import java.util.Optional;

import dev.noemontes.server.chat.dto.LogoutRequestDto;
import dev.noemontes.server.chat.exceptions.LoginException;
import dev.noemontes.server.chat.model.UserModel;

import dev.noemontes.server.chat.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.repository.UserMongoRepository;
import dev.noemontes.server.chat.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserMongoRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;

	@Autowired
	private ContactService contactService;
	
	@Override
	public UserDto login(LoginRequestDto userDto) throws LoginException {
		Optional<UserModel> opUserDb = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		
		if(opUserDb.isPresent()) {
			UserModel userDb = opUserDb.get();
			
			//Actualizamos el estado del contacto a conectado en base de datos
			userDb.setConnected(true);
			userRepository.save(userDb);

			//Notificamos a los contactos que se ha conectado
			contactService.notifyContacts(userDb.getUuid());
			
			return userConverter.convertModelToDto(userDb);
		}else {
			throw new LoginException("Error de inicio de sesion");
		}
	}

	@Override
	public UserDto logout(LogoutRequestDto logoutDto) {
		Optional<UserModel> opUserDb = userRepository.findById(logoutDto.getUserUuid());
		
		if(opUserDb.isPresent()) {
			UserModel userDb = opUserDb.get();
			
			//Actualizamos el estado del contacto a conectado en base de datos
			userDb.setConnected(false);
			userRepository.save(userDb);

			//notificamos a los contatos que se ha desconectado
			contactService.notifyContacts(logoutDto.getUserUuid());
			
			return userConverter.convertModelToDto(userDb);
		}else {
			throw new LoginException("Error de cierre de sesion");
		}
	}
}
