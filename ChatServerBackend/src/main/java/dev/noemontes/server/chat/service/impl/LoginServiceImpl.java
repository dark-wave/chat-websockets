package dev.noemontes.server.chat.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.noemontes.server.chat.converter.UserConverter;
import dev.noemontes.server.chat.dto.LoginRequestDto;
import dev.noemontes.server.chat.dto.UserDto;
import dev.noemontes.server.chat.model.UserModel;
import dev.noemontes.server.chat.repository.UserMongoRepository;
import dev.noemontes.server.chat.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private UserMongoRepository userRepository;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public UserDto login(LoginRequestDto userDto) {
		Optional<UserModel> opUserDb = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		
		if(opUserDb.isPresent()) {
			UserModel userDb = opUserDb.get();
			
			//Actualizamos el estado del contacto a conectado en base de datos
			userDb.setConnected(true);
			userRepository.save(userDb);
			
			return userConverter.convertModelToDto(userDb);
		}else {
			//TODO: Cambiar el retorno de null a una excepcion controlada de que el usuario no existe
			return null;
		}
	}

	@Override
	public UserDto logout(LoginRequestDto userDto) {
		Optional<UserModel> opUserDb = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
		
		if(opUserDb.isPresent()) {
			UserModel userDb = opUserDb.get();
			
			//Actualizamos el estado del contacto a conectado en base de datos
			userDb.setConnected(false);
			userRepository.save(userDb);
			
			return userConverter.convertModelToDto(userDb);
		}else {
			//TODO: Cambiar el retorno de null a una excepcion controlada de que el usuario no existe
			return null;
		}
	}
}
